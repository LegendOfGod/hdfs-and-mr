package MyOutput;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;


/**
 * @author lqb
 * @date 2021/10/11 10:06
 */
public class MyOutputFormat extends FileOutputFormat<Text, NullWritable> {
    @Override
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        FileSystem fileSystem = FileSystem.get(taskAttemptContext.getConfiguration());
        FSDataOutputStream bad = fileSystem.create(new Path("hdfs://172.24.250.142:8020/myOutputFormat_output/bad.text"));
        FSDataOutputStream good = fileSystem.create(new Path("hdfs://172.24.250.142:8020/myOutputFormat_output/good.text"));
        MyRecordWriter myRecordWriter = new MyRecordWriter(good, bad);
        return myRecordWriter;
    }
}
