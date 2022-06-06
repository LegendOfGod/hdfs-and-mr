import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author lqb
 * @date 2021/9/30 11:38
 */
public class hdfsTest {

    private static final String DEFAULT_HDFS_URL = "hdfs://lqbaliyun:9000/";
    private static final String HADOOP_USER_NAME = "root";

    private static FileSystem fileSystem = null;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.set("dfs.client.use.datanode.hostname", "true");
            fileSystem = FileSystem.get(new URI(DEFAULT_HDFS_URL), configuration,HADOOP_USER_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS",DEFAULT_HDFS_URL);
        FileSystem fileSystem = FileSystem.get(configuration);
        RemoteIterator<LocatedFileStatus> remoteFileIter = fileSystem.listFiles(new Path("/"), true);
        while (remoteFileIter.hasNext()){
            LocatedFileStatus next = remoteFileIter.next();
            System.out.println(next.getPath().toString());
        }
        System.out.println(fileSystem.toString());
    }

    @Test
    public void listMyFiles() throws URISyntaxException, IOException {
        FileSystem fileSystem = FileSystem.get(new URI(DEFAULT_HDFS_URL), new Configuration());
        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(new Path("/"), true);
        while (locatedFileStatusRemoteIterator.hasNext()) {
            LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
            System.out.println(next.getPath().toString());
        }
        fileSystem.close();
    }

    @Test
    public void mkdir() throws Exception{
        FileSystem fileSystem = FileSystem.get(new URI(DEFAULT_HDFS_URL), new Configuration(),HADOOP_USER_NAME);
        boolean mkdirs = fileSystem.mkdirs(new Path("/data/testdata2"));
        fileSystem.close();
    }

    @Test
    public void putData() throws Exception{
        fileSystem.copyFromLocalFile(new Path("file:///C:\\Users\\Administrator\\Downloads\\售前每日报表企微端.xlsx")
                ,new Path("/data/testdata2"));
        fileSystem.close();
    }

    @Test
    public void getData() throws Exception{
        fileSystem.copyToLocalFile(new Path("/data/testdata2/售前每日报表企微端.xlsx"),
                new Path("file:///C:\\Users\\Administrator\\Desktop\\AA.xlsx"));
        fileSystem.close();
    }
}
