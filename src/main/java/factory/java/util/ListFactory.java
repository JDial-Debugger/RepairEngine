package factory.java.util;

import java.util.List;

public interface ListFactory {
	<TContents> List<TContents> getEmptyArrayList();

	<TContents> List<TContents> getListFrom(TContents[] array);
}
