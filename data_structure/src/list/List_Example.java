package list;

import java.io.*;
import java.util.*;

public class List_Example {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;


        String file_path = "./List_Sample.txt";
        File file = new File(file_path);

        List<GradeVO> list = new ArrayList<>();

        if (file.exists()) {
            BufferedReader inFile = new BufferedReader(new FileReader(file));
            String inLine = null;

            while ((inLine = inFile.readLine()) != null) {
                GradeVO vo = new GradeVO(inLine);
                list.add(vo);
            }
        }


        while (flag) {
            String input = scanner.nextLine();
            if ("quit".equalsIgnoreCase(input)) {
                System.exit(0);
            } else {
                if ("PRINT".equalsIgnoreCase(input)) {
                    Collections.sort(list, new Comparator<GradeVO>() {
                        @Override
                        public int compare(GradeVO o1, GradeVO o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });

                } else if ("KOREAN".equalsIgnoreCase(input)) {
                    Collections.sort(list, (o1, o2) -> o2.getKorean() - o1.getKorean() == 0 ? o1.getName().compareTo(o2.getName()) : o2.getKorean() - o1.getKorean() );
                } else if ("ENGLISH".equalsIgnoreCase(input)) {
                    Collections.sort(list, (o1, o2) -> o2.getEnglish() - o1.getEnglish() == 0 ? o1.getName().compareTo(o2.getName()) : o2.getEnglish() - o1.getEnglish() );
                } else if ("MATH".equalsIgnoreCase(input)) {
                    Collections.sort(list, (o1, o2) -> o2.getMath() - o1.getMath() == 0 ? o1.getName().compareTo(o2.getName()) : o2.getMath() - o1.getMath() );

                }

                for (GradeVO result : list) {
                    System.out.println(result);

                }
            }

        }


//
//        while(flag) {
//
//
//        if ("quit".equalsIgnoreCase(input)) {
//            System.exit(0);
//        } else {
//
//            File file = new File(file_path);
//
//            if (file.exists()) {
//                BufferedReader inFile = new BufferedReader(new FileReader(file));
//
//                String inLine = null;
//
//                while ((inLine = inFile.readLine()) != null) {
//                    ResultVO vo = new ResultVO(inLine);
//                    list.add(vo);
//                }
//            }
//
//
//            if ("PRINT".equalsIgnoreCase(input)) {
//                Collections.sort(list, new Comparator<ResultVO>() {
//                    @Override
//                    public int compare(ResultVO o1, ResultVO o2) {
//                        return o1.getName().compareTo(o2.getName());
//                    }
//                });
//
//
//
//            } else if ("KOREAN".equalsIgnoreCase(input)) {
//
//            } else if ("ENGLISH".equalsIgnoreCase(input)) {
//
//            } else if ("MATH".equalsIgnoreCase(input)) {
//
//            }
//        }
//        }
    }

}