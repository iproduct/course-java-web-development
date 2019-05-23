package invoicing.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import invoicing.exceptions.EntityDoesNotExistException;
import invoicing.exceptions.EntityExistsException;
import invoicing.exceptions.FileIOException;
import invoicing.model.Measure;
import invoicing.model.Product;

public class BinaryFileProductRepositoryImpl extends ProductRepositoryImpl implements ProductRepository {
	protected String repoFileName;
	protected Logger log = Logger.getLogger("BinaryFileProductRepositoryImpl.class");
	
	public BinaryFileProductRepositoryImpl() {
		repoFileName = "products.db";
	}

	public BinaryFileProductRepositoryImpl(String filename) {
		repoFileName = filename;
	}

	@Override
	public Optional<Product> findById(String id) {
		checkLoadDb();
		return super.findById(id);
	}

	@Override
	public Collection<Product> findAll() {
		checkLoadDb();
		return super.findAll();
	}

	@Override
	public Product add(Product entity) throws EntityExistsException {
		checkLoadDb();
		Product result = super.add(entity);
		try {
			saveProducts();
		} catch (IOException e) {
			throw new FileIOException(e);
		}
		return result;
	}

	@Override
	public Product update(Product entity) throws EntityDoesNotExistException {
		checkLoadDb();
		Product result = super.update(entity);
		try {
			saveProducts();
		} catch (IOException e) {
			throw new FileIOException(e);
		}
		return result;
	}

	@Override
	public Optional<Product> delete(String id) {
		checkLoadDb();
		Optional<Product> result = super.delete(id);
		try {
			saveProducts();
		} catch (IOException e) {
			throw new FileIOException(e);
		}
		return result;
	}

	/* ===================   Utility methods ======================*/
	
	protected void checkLoadDb() {
		if (entries.size() == 0) {
			try {
				loadProducts();
			} catch (IOException e) {
				throw new FileIOException(e);
			}
		}
	}

	protected void saveProducts() throws IOException {
		try( DataOutputStream os = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(repoFileName))) ){
			for(Product p: entries.values()){
				writeProduct(os, p);	
			}
			log.info("Products saved to file " + repoFileName);
		}
			
	}

	protected void writeProduct(DataOutputStream os, Product p) throws IOException {
		os.writeUTF(p.getId());
		os.writeUTF(p.getName());
		os.writeDouble(p.getPrice());
		os.writeInt(p.getMeasure().ordinal());
	}

	protected void loadProducts() throws IOException {
		try( DataInputStream os = new DataInputStream(
				new BufferedInputStream(new FileInputStream(repoFileName))) ){
			try{
				entries.clear();
				while(true){
					Product p = readProduct(os);
					entries.put(p.getId(), p);
				}
			} catch (EOFException e){}
			log.fine("Products loaded from file " + repoFileName);
		}
	}

	protected Product readProduct(DataInputStream os) throws IOException {
		Product p = new Product();
		p.setId(os.readUTF());
		p.setName(os.readUTF());
		p.setPrice(os.readDouble());
		p.setMeasure(Measure.values()[os.readInt()]);
		return p;
	}

	public static void main(String[] args) throws EntityExistsException, EntityDoesNotExistException {
			ProductRepository repo = new BinaryFileProductRepositoryImpl();
//			repo.add(new Product("BK1126", "Thinking in Java", 25.70, PCS));
//			repo.add(new Product("CA4218", "Computer Mouse", 12.99, PCS));
//			repo.add(new Product("HA0019", "Network cable", 2.17, M));
//			repo.add(new Product("AX9972", "Copier paper", 12.30, PCS));
//			for(Product p : repo.findAll()) {
//				System.out.println(p);
//			}
			
			System.out.println();
//			repo.update(new Product("BK1125", "Effective Java", 35.70, PCS));
			for(Product p : repo.findAll()) {
				System.out.println(p);
			}

	}

}
