package File_format;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Meta_data;

/**
 * this class implements GIS_layer,
 * and represents a Csv_layer that contains a set of Csv_elements
 * @author Eitan Lichtman, Netanel Indik
 */
public class Csv_layer implements GIS_layer {

	private Set<Csv_element> layer;

	/**
	 * regular constructor
	 */
	public Csv_layer() {
		layer = new HashSet<Csv_element>();
	}
	
	/**
	 * copy constructor
	 * @param csv_layer
	 */
	public Csv_layer(Csv_layer csv_layer) {
		layer = new HashSet<Csv_element>();
		Iterator<Csv_element> it = csv_layer.iterator_csv();
		while(it.hasNext()) {
			Csv_element element = it.next();
			layer.add(element);
		}
	}

	/**
	 * adds an element to this layer
	 */
	@Override
	public boolean add(GIS_element element) {
		return layer.add((Csv_element) element);
	}

	/**
	 * adds all elements from collection to this layer
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_element> elements) {
		return layer.addAll((Collection<? extends Csv_element>) elements);
	}

	/**
	 * deletes all elements from layer
	 */
	@Override
	public void clear() {
		layer.clear();		
	}

	/**
	 * returns true iff this layer contains a given element
	 */
	@Override
	public boolean contains(Object obj) {
		return layer.contains(obj);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return layer.containsAll(c);
	}

	/**
	 * returns true if layer is empty
	 */
	@Override
	public boolean isEmpty() {
		return layer.isEmpty();
	}

	
	@Override
	public Iterator<GIS_element> iterator() {
		return null;
	}
	
	/**
	 * returns the layer iterator
	 */
	public Iterator<Csv_element> iterator_csv() {
		return layer.iterator();
	}

	/**
	 * removes a given object
	 */
	@Override
	public boolean remove(Object obj) {
		return layer.remove(obj);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return layer.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return layer.retainAll(c);
	}

	/**
	 * returns layers size
	 */
	@Override
	public int size() {
		return layer.size();
	}
	
	@Override
	public Object[] toArray() {
		return layer.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return (T[]) layer.toArray(a);
	}

	/**
	 * returns the meta data representing this layer
	 */
	@Override
	public Meta_data get_Meta_data() {
		Iterator<Csv_element> it = this.iterator_csv();
		ArrayList<String[]> md = new ArrayList<String[]>();
		Csv_element element = it.next();
		md.add(element.getMeta_data().getData().get(0));
		do{
			md.add(element.getMeta_data().getData().get(1));
		Csv_meta_data current = element.getMeta_data();
		element = it.next();
		}
		while(it.hasNext());
		return new Csv_meta_data (md);
	}

	public Set<Csv_element> getLayer() {
		return layer;
	}

	private void setLayer(Set ot) {
		this.clear();
		Iterator<Csv_element> it = ot.iterator();
		while(it.hasNext()) {
			add(it.next());
		}
	}

	/**
	 * returns a String representing this layer
	 */
	public String toString () {
		String s ="";
		Iterator<Csv_element> it = this.iterator_csv();
		while(it.hasNext()) {
			s = s + it.next().toString() + "\n";
		}
		return s;
	}
	
}
