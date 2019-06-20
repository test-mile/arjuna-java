package arjuna.tpi.ddauto;

import arjuna.client.databroker.DataContainerBuilder;
import arjuna.client.databroker.FileDataSourceBuilder;
import arjuna.client.databroker.SetuDataSourceBuilder;
import arjuna.client.testsession.TestSession;

public class DataSourceBuilder{
	private TestSession session;
	
	public DataSourceBuilder(TestSession session) {
		this.session = session;
	}
	
	public FileDataSourceBuilder<ListDataSource> fileListDataSource(String fileName) {
		return SetuDataSourceBuilder.fileListDataContainer(session, fileName);
	}
	
	public FileDataSourceBuilder<MapDataSource> fileMapDataSource(String fileName) {
		return SetuDataSourceBuilder.fileMapDataContainer(session, fileName);
	}
	
	public DataContainerBuilder<ListDataSource> listDataContainer() {
		return SetuDataSourceBuilder.listDataContainer();
	}
	
	public DataContainerBuilder<MapDataSource> mapDataContainer(String... headers) throws Exception {
		return SetuDataSourceBuilder.mapDataContainer(headers);
	}
}


