package partition;

import model2.PairWritable;
import model2.SortJobMain;
import model2.SortMapper;
import model2.SortReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author lqb
 * @date 2021/10/8 16:20
 */
public class PartitionJobMain extends Configured implements Tool {
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(super.getConf(), SortJobMain.class.getSimpleName());
        //打包到集群环境添加
        job.setJarByClass(PartitionJobMain.class);
        //读取文件key-value
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("hdfs://lqbaliyun:9000/data/partition"));
        //设置mapper 输出key和value
        job.setMapperClass(PartitionMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        //设置reduce 输出key和value
        job.setReducerClass(PartitionReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        //设置输出文件位置
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("hdfs://lqbaliyun:9000/data/partition_out"));
        //分区设置
        job.setPartitionerClass(MyPartition.class);
        job.setNumReduceTasks(2);
        boolean completion = job.waitForCompletion(true);
        return completion?0:1;
    }

    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        PartitionJobMain jobMain = new PartitionJobMain();
        ToolRunner.run(configuration,jobMain,args);
    }
}
