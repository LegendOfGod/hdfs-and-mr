package common_friend_2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author lqb
 * @date 2021/10/9 15:13
 */
public class Friend2MainJob extends Configured implements Tool {
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(super.getConf(), Friend2MainJob.class.getSimpleName());
        job.setJarByClass(Friend2MainJob.class);
        //输入
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("hdfs://172.24.250.142:8020/common_friend_out"));
        //mapper
        job.setMapperClass(Friend2Mapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        //reducer
        job.setReducerClass(Friend2Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //输出
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("hdfs://172.24.250.142:8020/common_friend_out2"));
        boolean completion = job.waitForCompletion(true);
        return completion?0:1;
    }

    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();
        Friend2MainJob friend2MainJob = new Friend2MainJob();
        int run = ToolRunner.run(configuration, friend2MainJob, args);
        System.exit(run);
    }
}
