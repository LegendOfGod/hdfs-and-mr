package model2;



import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/8 15:17
 */
public class PairWritable implements WritableComparable<PairWritable> {

    private String first;
    private int second;

    public PairWritable(){}

    public PairWritable(String first, int second) {
        this.first = first;
        this.second = second;
    }

    public void set(String first,int second){
        this.first = first;
        this.second = second;
    }



    public int compareTo(PairWritable o) {
        System.out.println(o.toString());
        System.out.println(this.toString());
        int comp = this.first.compareTo(o.first);
        if(comp != 0){
            return comp;
        }else {
            return Integer.valueOf(this.second).compareTo(o.second);
        }
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(first);
        dataOutput.writeInt(second);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.first = dataInput.readUTF();
        this.second = dataInput.readInt();
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "PairWritable{" +
                "first='" + first + '\'' +
                ", second=" + second +
                '}';
    }
}
