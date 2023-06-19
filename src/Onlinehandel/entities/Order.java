package Onlinehandel.entities;

import java.beans.Customizer;
import java.util.*;

import Onlinehandel.provided.*;

/**
 * An order within the Onlinehandel.<br>
 * <br>
 * 
 * An order holds information on the costumer placing the order, the items
 * ordered and the status of the order which captures collection and delivery
 * date and time.<br>
 * <br>
 * 
 * The usual life cycle is
 * <ul>
 * <li>create an order with id, costumer and at least one item</li>
 * <li>add items</li>
 * <li>collect</li>
 * <li>deliver</li>
 * </ul>
 *
 */
public abstract class Order implements Comparable<Order> {
	private DateTime collected;
	private Costumer costumer;
	private DateTime delivered;
	private Set<Item> goods;
	private long id;


	public Order(long id, Costumer c, Iterable<Item> items) {
		if (id < 1 || c == null || items == null)
			throw new IllegalArgumentException();
		this.id = id;
		this.costumer = c;
		// nachfragen was das macht
		goods = new HashSet<>();
		for (Item item : items){
			goods.add(item);
		}

		//addItems(items);
	}

	public Order(Order orig){
		this.id = orig.id;
		this.collected = new DateTime(orig.collected);
		this.delivered = new DateTime(orig.delivered);
		this.costumer = new Costumer(orig.costumer);
		this.goods = new HashSet<>(orig.goods);
	}

	public abstract int getTotal();

	public boolean isCollected(){
		if (collected != null) return true;
		return false;
	}

	public boolean isDelivered(){
		if (delivered != null) return true;
		return false;
	}

	public final boolean addItems(Item item){
		if (isCollected() == false && isDelivered() == false){
			goods.add(item);
			return true;
		}
		return false;
	}

	// nachfragen
	public final boolean addItems (Iterable<Item> items){
		if (isCollected() == false && isDelivered() == false){
			for( Item item : items){
				if (!goods.contains(item)){
					goods.add(item);
				}
			}
			return true;
		}
		return false;
	}

	public Set<Item> getItems(){
		return new HashSet<>(goods);
	}

	public final boolean collect(DateTime toc){
		if (!isCollected()){
			collected = new DateTime(toc);
			return true;
		}
		return false;
	}

	public final boolean deliver(DateTime tod){
		if (!isCollected() && !isDelivered()){
			delivered = new DateTime(tod);
			return true;
		}
		return false;
	}

	private final String ensureNonNullNonEmpty(String str){
		if (str == null || str.isEmpty()){
			throw new IllegalArgumentException();
		} else {
			return str;
		}
	}

	@Override
	public int compareTo(Order arg0){
		return Long.compare(id, arg0.id);
	}

	public Costumer getCostumer() {
		return new Costumer(costumer);
	}

	/**
	 * creates a string representation of this delivery.<br>
	 *
	 * @ProgrammingProblem.Hint provided
	 *
	 */
	@Override
	public String toString() {
		return String.format("%d " + "[%scollected, %sdelivered] (EUR %.2f)\n" + "%s", //
				id, isCollected() ? "" : "not ", isDelivered() ? "" : "not ", getTotal() / 100.,
				goods == null ? "no stock" : goods);
	}

}
