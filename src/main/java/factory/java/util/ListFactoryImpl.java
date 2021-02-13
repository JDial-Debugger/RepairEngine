package factory.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListFactoryImpl implements ListFactory {

	@Override
	public <TContents> List<TContents> createEmptyArrayList() {
		return new ArrayList<TContents>();
	}

	@Override
	public <TContents> List<TContents> createListFrom(TContents[] array) {
		return Arrays.asList(array);
	}
}
