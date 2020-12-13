package factory.java.lang;

public class StringBuilderFactoryImpl implements StringBuilderFactory {
	@Override
	public StringBuilder getStringBuilder() {
		return new StringBuilder();
	}
}
