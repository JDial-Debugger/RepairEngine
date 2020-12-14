package factory.java.util;

import java.util.HashSet;
import java.util.Set;

public class SetFactoryImpl implements SetFactory {
	@Override
	public <TContents> Set<TContents> getHashSet() {
		return new HashSet<TContents>();
	}
}
