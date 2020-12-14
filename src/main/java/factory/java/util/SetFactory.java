package factory.java.util;

import java.util.Set;

public interface SetFactory {
	public <TContents> Set<TContents> getHashSet();
}
