package hbase_mapreduce.test2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author lqb
 * @date 2021/10/29 11:08
 */
public class HdfsMapReducerMainJob extends Configured implements Tool {
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(super.getConf(), HdfsMapReducerMainJob.class.getSimpleName());
        job.setJarByClass(HdfsMapReducerMainJob.class);

        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("hdfs://node01:8020/hbase/input"));

        job.setMapperClass(HdfsMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        //分区
        //排序
        //规约
        //分组

        //reduce
        TableMapReduceUtil.initTableReducerJob("myuser2",HdfsReducer.class,job);
        boolean completion = job.waitForCompletion(true);
        return completion?0:1;
    }

    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum","node01:2181,node02:2181,node03:2181");
        int run = ToolRunner.run(configuration, new HdfsMapReducerMainJob(), args);
        System.exit(run);
    }
}
