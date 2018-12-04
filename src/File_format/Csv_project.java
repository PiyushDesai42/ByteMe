package File_format;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Meta_data;

/**
 * this class represents a Csv_project that contains a set of Csv_layers
 * @author Eitan Lichtman, Netanel Indik
 */
public class Csv_project implements GIS_project{

	private Set<Csv_layer> project;

	/**
	 * regular constructor
	 */
	public Csv_project() {
		project = new HashSet<Csv_layer>();
	}
	
	/**
	 * copy constructor
	 * @param csv_project
	 */
	public Csv_project(Csv_project csv_project) {
		project = new HashSet<Csv_layer>();
		Iterator<Csv_layer> it = csv_project.iterator_csv();
		while(it.hasNext()) {
			Csv_layer layer = it.next();
			project.add(layer);
		}
	}
		
		
		
	/**
	 * adds a layer to this project
	 */
	@Override
	public boolean add(GIS_layer layer) {
		return project.add((Csv_layer) layer);
	}

	/**
	 * adds all layers from collection to this project
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_layer> layers) {
		return project.addAll((Collection<? extends Csv_layer>) layers);
	}

	/**
	 * deletes all layers from project
	 */
	@Override
	public void clear() {
		project.clear();		
	}

	/**
	 * returns true iff this project contains a given layer
	 */
	@Override
	public boolean contains(Object obj) {
		return project.contains(obj);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return project.containsAll(c);
	}

	/**
	 * returns true if project is empty
	 */
	@Override
	public boolean isEmpty() {
		return project.isEmpty();
	}

	
	@Override
	public Iterator<GIS_layer> iterator() {
		return null;
	}
	
	/**
	 * returns the project iterator
	 */
	public Iterator<Csv_layer> iterator_csv() {
		return project.iterator();
	}

	/**
	 * removes a given object
	 */
	@Override
	public boolean remove(Object obj) {
		return project.remove(obj);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return project.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return project.retainAll(c);
	}

	/**
	 * returns project size
	 */
	@Override
	public int size() {
		return project.size();
	}
	
	@Override
	public Object[] toArray() {
		return project.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return (T[]) project.toArray(a);
	}

	/**
	 * returns the meta data representing this project
	 */
	@Override
	public Meta_data get_Meta_data() {
		ArrayList<String[]> md = new ArrayList<String[]>();
		Iterator<Csv_layer> it_layer= this.iterator_csv();
		while(it_layer.hasNext()) {
			Csv_layer layer = it_layer.next();
		Iterator<Csv_element> it_element = layer.iterator_csv();
		Csv_element element = it_element.next();
		md.add(element.getMeta_data().getData().get(0));          //adding title Array
		do{
			md.add(element.getMeta_data().getData().get(1));      //adding data Arrays
		Csv_meta_data current = element.getMeta_data();
		element = it_element.next();
		}
		while(it_element.hasNext());
		String [] space = {"\n"};
		md.add(space);                                            //addding a space between layers
		}
		return new Csv_meta_data (md);
	}

	public Set<Csv_layer> getProject() {
		return project;
	}

	private void setProject(Set ot) {
		this.clear();
		Iterator<Csv_layer> it = ot.iterator();
		while(it.hasNext()) {
			add(it.next());
		}
	}

	/**
	 * returns a String representing this project
	 */
	public String toString () {
		String s ="";
		Iterator<Csv_layer> it = this.iterator_csv();
		while(it.hasNext()) {
			s = s + it.next().toString() + "\n";
		}
		return s;
	}
}
