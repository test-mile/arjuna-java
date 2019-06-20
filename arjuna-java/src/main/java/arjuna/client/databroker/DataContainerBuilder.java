package arjuna.client.databroker;

public interface DataContainerBuilder<T> {

	DataContainerBuilder<T> record(Object...objects) throws Exception;

	T build();

}
