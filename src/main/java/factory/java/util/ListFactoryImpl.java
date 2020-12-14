package factory.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListFactoryImpl implements ListFactory {

	@Override
	public <TContents> List<TContents> getEmptyArrayList() {
		return new ArrayList<TContents>();
	}

	@Override
	public <TContents> List<TContents> getListFrom(TContents[] array) {
		return Arrays.asList(array);
	}
}
