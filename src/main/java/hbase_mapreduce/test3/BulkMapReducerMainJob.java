package hbase_mapreduce.test3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author lqb
 * @date 2021/10/29 13:45
 */
public class BulkMapReducerMainJob extends Configured implements Tool {
    public int run(String[] strings) throws Exception {
        Configuration conf = super.getConf();
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("myuser2"));
        Job job = Job.getInstance(conf, BulkMapReducerMainJob.class.getSimpleName());
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("hdfs://node01:8020/hbase/input"));
        //mapper
        job.setMapperClass(BulkLoadMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);

        HFileOutputFormat2.configureIncrementalLoad(job,table,connection.getRegionLocator(TableName.valueOf("myuser2")));
        job.setOutputFormatClass(HFileOutputFormat2.class);
        HFileOutputFormat2.setOutputPath(job,new Path("hdfs://node01:8020/hbase/hfile_out"));
        boolean completion = job.waitForCompletion(true);
        return completion?0:1;
    }

    public static void main(String[] args) throws Exception{
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","node01:2181,node02:2181,node03:2181");
        int run = ToolRunner.run(configuration, new BulkMapReducerMainJob(), args);
        System.exit(run);
    }
}
