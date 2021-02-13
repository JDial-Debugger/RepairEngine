package factory.java.util;

import java.util.Stack;

public class StackFactoryImpl implements StackFactory {

	@Override
	public <TContents> Stack<TContents> createStack() {
		return new Stack<TContents>();
	}
}
