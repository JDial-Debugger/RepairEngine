package factory.java.util;

import java.util.List;

public interface ListFactory {
	List getEmptyArrayList();

	<TContents> List<TContents> getListFrom(TContents[] array);
}
