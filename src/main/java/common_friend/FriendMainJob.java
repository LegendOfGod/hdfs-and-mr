package common_friend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import product_order_2.MapperJoinMainJob;
import product_order_2.MapperJoinMapper;
import product_order_2.MapperJoinReducer;

import java.net.URI;

/**
 * @author lqb
 * @date 2021/10/9 14:21
 */
public class FriendMainJob extends Configured implements Tool {

    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(super.getConf(), FriendMainJob.class.getSimpleName());
        job.setJarByClass(FriendMainJob.class);
        //输入
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("hdfs://172.24.250.142:8020/common_friend"));
        //mapper
        job.setMapperClass(FriendMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        //reducer
        job.setReducerClass(FriendReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //输出
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path("hdfs://172.24.250.142:8020/common_friend_out"));
        boolean completion = job.waitForCompletion(true);
        return completion ? 0 : 1;
    }

    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();
        FriendMainJob mainJob = new FriendMainJob();
        int run = ToolRunner.run(configuration, mainJob, args);
        System.exit(run);
    }
}
