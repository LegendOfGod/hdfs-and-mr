package hbase_mapreduce.test1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author lqb
 * @date 2021/10/29 9:59
 */
public class HBaseMapReducerMainJob extends Configured implements Tool {
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(super.getConf(), HBaseMapReducerMainJob.class.getSimpleName());
        Scan scan = new Scan();
        TableMapReduceUtil.initTableMapperJob("myuser",scan,HBaseMapper.class, Text.class, Put.class,job);
        TableMapReduceUtil.initTableReducerJob("myuser2",HBaseReducer.class,job);
        boolean completion = job.waitForCompletion(true);
        return completion?0:1;
    }

    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum","node01:2181,node02:2181,node02:2181");
        int run = ToolRunner.run(configuration, new HBaseMapReducerMainJob(), args);
        System.exit(run);

    }
}
