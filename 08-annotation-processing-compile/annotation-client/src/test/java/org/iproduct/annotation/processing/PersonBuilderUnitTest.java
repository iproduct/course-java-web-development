package org.iproduct.annotation.processing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.iproduct.annotation.client.Person;
import org.iproduct.annotation.client.PersonBuilder;
import org.junit.jupiter.api.Test;

public class PersonBuilderUnitTest {

    @Test
    public void whenBuildPersonWithBuilder_thenObjectHasPropertyValues() {

        Person person = new PersonBuilder().setAge(25).setName("John").build();

        assertEquals(25, person.getAge());
        assertEquals("John", person.getName());

    }

}
