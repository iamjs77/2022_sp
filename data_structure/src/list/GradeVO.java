package list;

public class GradeVO {
    private String name;
    private int korean;
    private int english;
    private int math;

    public GradeVO(String line) {

        String[] arr = line.split("\t");

        this.name = arr[0];
        this.korean = Integer.parseInt(arr[1]);
        this.english = Integer.parseInt(arr[2]);
        this.math = Integer.parseInt(arr[3]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKorean() {
        return korean;
    }

    public void setKorean(int korean) {
        this.korean = korean;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    @Override
    public String toString() {
        return  String.format("%s\t%d\t%d\t%d", getName(), getKorean(), getEnglish(), getMath());

//
//       StringBuffer sb = new StringBuffer();
//       sb.append(this.getName()).append("\t");
//       sb.append(this.getKorean()).append("\t");
//       sb.append(this.getEnglish()).append("\t");
//       sb.append(this.getMath());
//
//       return sb.toString();
    }
}
