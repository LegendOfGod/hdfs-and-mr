package MyOutput;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathAccessDeniedException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author lqb
 * @date 2021/10/11 10:18
 */
public class MyOutputFormatJobMain extends Configured implements Tool {
    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(super.getConf(), MyOutputFormatJobMain.class.getSimpleName());
        job.setJarByClass(MyOutputFormatJobMain.class);
        //输入
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("hdfs://172.24.250.142:8020/myOutputFormat_input"));
        //mapper
        job.setMapperClass(MyOutputFormatMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        //reduce
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        //输出
        job.setOutputFormatClass(MyOutputFormat.class);
        MyOutputFormat.setOutputPath(job,new Path("hdfs://172.24.250.142:8020/myOutputFormat_output"));

        boolean completion = job.waitForCompletion(true);
        return completion?0:1;
    }

    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://172.24.250.142:8020/");
        MyOutputFormatJobMain jobMain = new MyOutputFormatJobMain();
        int run = ToolRunner.run(configuration, jobMain, args);
        System.exit(run);
    }
}
