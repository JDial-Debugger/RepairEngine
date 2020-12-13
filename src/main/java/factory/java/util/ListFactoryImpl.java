package factory.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListFactoryImpl implements ListFactory {

	@Override
	public List getEmptyArrayList() {
		return new ArrayList();
	}

	@Override
	public <TContents> List<TContents> getListFrom(TContents[] array) {
		return Arrays.asList(array);
	}
}
