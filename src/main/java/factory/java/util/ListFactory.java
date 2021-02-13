package factory.java.util;

import java.util.List;

public interface ListFactory {
	<TContents> List<TContents> createEmptyArrayList();

	<TContents> List<TContents> createListFrom(TContents[] array);
}
