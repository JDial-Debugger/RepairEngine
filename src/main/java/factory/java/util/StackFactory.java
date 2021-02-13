package factory.java.util;

import java.util.Stack;

public interface StackFactory {
	<TContents> Stack<TContents> createStack();
}
