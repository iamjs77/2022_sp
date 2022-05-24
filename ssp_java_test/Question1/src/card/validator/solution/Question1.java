package card.validator.solution;

import card.validator.model.InspectorInfo;
import card.validator.utils.CardUtility;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Question1 {

    Map<String, String> inspectorMap = new HashMap<>();

    public String solution()  {

        String result = "";

        try {
            //1. load Inspectors.txt
            loadInspectorFile();

            //2. console 입력
            readyInputByConsole();

        } catch (Exception e) {
            e.printStackTrace();
        }



        return result;
    }

    private void readyInputByConsole() {
        Scanner scanner = new Scanner(System.in);

        while(true) {

            String input = scanner.nextLine();
            InspectorInfo inspecotor = new InspectorInfo(input);

            if(checkInspectorInfo( inspecotor)) {
                System.out.println("LOGIN SUCCESS");
            } else {
                System.out.println("LOGIN FAIL");
            }

        }
    }

    private boolean checkInspectorInfo(InspectorInfo inspecotor) {
        boolean result = false;
        String expectedPwd = inspectorMap.get(inspecotor.id);
        String encPwd = "";
        try {
            encPwd = CardUtility.passwordEncryption(inspecotor.pwd);
        } catch (NoSuchAlgorithmException e) {
            result = false;
        }

        result = expectedPwd.equals(encPwd);


        return result;
    }

    public void loadInspectorFile() throws Exception {
        Path path = Paths.get("../CLIENT/INSPECTOR.TXT");
        Charset charset = Charset.forName("UTF-8");
        List<String> lines = Files.readAllLines(path, charset);
        for (String line : lines) {
            // inspectors 생성
            String[] infos = line.split(" ");

            inspectorMap.put(infos[0], infos[1]);
        }


    }
}
