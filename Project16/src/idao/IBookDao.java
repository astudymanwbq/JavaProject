package idao;

import java.util.ArrayList;

import po.Book;

public interface IBookDao {
	public ArrayList queryByPriceRange(int low, int high);
	public ArrayList queryByName(String name);
	public int add(Book book);
	public int update(String bid, int amount);
}
