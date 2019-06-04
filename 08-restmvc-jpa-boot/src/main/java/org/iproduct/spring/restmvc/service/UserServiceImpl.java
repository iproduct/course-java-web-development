package org.iproduct.spring.restmvc.service;

import lombok.extern.slf4j.Slf4j;
import org.iproduct.spring.restmvc.dao.UserRepository;
import org.iproduct.spring.restmvc.events.UserCreationEvent;
import org.iproduct.spring.restmvc.exception.EntityNotFoundException;
import org.iproduct.spring.restmvc.exception.InvalidEntityIdException;
import org.iproduct.spring.restmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
@Slf4j
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    // single TransactionTemplate shared amongst all methods in this instance
    private final PlatformTransactionManager transactionManager;

    // single TransactionTemplate shared amongst all methods in this instance
    private final TransactionTemplate transactionTemplate;

    // use constructor-injection to supply the PlatformTransactionManager
    public UserServiceImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }

    @Override
    public User createUser(User user) {
        repo.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new InvalidEntityIdException(String.format("User with username '%s' already exists.", user.getUsername()));
        });
        user.setCreated(new Date());
        user.setUpdated(new Date());
        if(user.getRoles() == null || user.getRoles().length() == 0) {
            user.setRoles("ROLE_USER");
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        return repo.save(user);
    }

    @Override
    public User updateUser(User user) {
        user.setUpdated(new Date());
        return repo.save(user);
    }

    @Override
    public User getUserById(long id) {
        return repo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with ID=%s not found.", id)));
    }

    @Override
    public User getUserByUsername(String username) {
        return repo.findByUsername(username).orElseThrow(() ->
                new EntityNotFoundException(String.format("User '%s' not found.", username)));
    }

    @Override
    public User deleteUser(long id) {
        User old = repo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with ID=%s not found.", id)));
        repo.deleteById(id);
        return old;
    }

    @Override
    public long getUsersCount() {
        return repo.count();
    }

    // Declarative transaction
    @Transactional
    public List<User> createUsersBatch(List<User> users) {
        List<User> created = users.stream()
                .map(user -> {
                    User resultUser = createUser(user);
                    applicationEventPublisher.publishEvent(new UserCreationEvent(resultUser));
                    return resultUser;
                }).collect(Collectors.toList());
        return created;
    }

    @TransactionalEventListener
    public void handleUserCreatedTransactionCommit(UserCreationEvent creationEvent) {
        log.info(">>> Transaction COMMIT for user: {}", creationEvent.getUser());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleUserCreatedTransactionRollaback(UserCreationEvent creationEvent) {
        log.info(">>> Transaction ROLLBACK for user: {}", creationEvent.getUser());
    }
}
