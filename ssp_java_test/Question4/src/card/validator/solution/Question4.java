package card.validator.solution;

import card.validator.model.CardInfo;
import card.validator.model.InspectionResultVo;
import card.validator.model.InspectorInfo;
import card.validator.utils.CardUtility;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.*;

public class Question4 {

    Map<String, String> inspectorMap = new HashMap<>();
    InspectorInfo currentInspectorInfo = null;
    String currentBusId = null;

    String startInspectionTime;

    public void solution() {

        try {
            //1. load Inspectors.txt
            loadInspectorFile();

            //2. console 입력
            readyInputByConsole();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readyInputByConsole() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            String input = scanner.nextLine();

            if (input.startsWith("INSP")) {
                InspectorInfo inspecotor = new InspectorInfo(input);
                checkLogin(inspecotor);

            } else if (input.startsWith("BUS")) {
                currentBusId = input;
                startInspectionTime = CardUtility.getCurrentDateTimeString();

            } else if (input.startsWith("CARD")) {
                handleCardInfo(input);
            } else if ("DONE".equalsIgnoreCase(input)) {
                // 검사완료
                currentBusId = null;
                currentInspectorInfo = null;

            } else if ("LOGOUT".equalsIgnoreCase(input)) {
                sendAndBackUpFiles();
                System.exit(0);
            }

        }
    }

    private void sendAndBackUpFiles() throws IOException {
        // File List
        File dir = new File("../" + currentInspectorInfo.id);

        File[] files = dir.listFiles();

        sendFiles(files);
        backupFiles(files);


    }

    private void backupFiles(File[] files) throws IOException {

        File backUp = new File("../BACKUP");
        for(File file : files) {
        File destFileName = new File(backUp, file.getName() );
            Files.copy(file.toPath(), destFileName.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

    }

    private void sendFiles(File[] files) throws IOException {
        if (files.length > 0) {
            Socket socket = null;
            DataOutputStream os = null;
            try {
                socket = new Socket("127.0.0.1", 27015);
                os = new DataOutputStream(socket.getOutputStream());

                byte[] buffer = new byte[4096];
                int length;

                for (File file : files) {
                    if (file.isFile()) {
                        // 파일이름 전송
                        os.writeUTF(file.getName());
                        // 파일크기 전송
                        os.writeInt((int) file.length());

                        FileInputStream is = null;
                        try {
                            is = new FileInputStream(file.getPath());
                            while ((length = is.read(buffer)) != -1) {
                                // 파일내용 전송
                                os.write(buffer, 0, length);
                            }
                        } finally {
                            if (is != null) {
                                is.close();
                            }
                        }
                    }
                }
                System.out.println("Sent All Files.");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (os != null) {
                    os.close();
                }
                if (socket != null) {
                    socket.close();
                }
            }
        }
    }

    private void handleCardInfo(String input) {
        CardInfo cardInfo = new CardInfo(input);

        inspectCardInfo(cardInfo);

    }

    private void inspectCardInfo(CardInfo cardInfo) {
        InspectionResultVo result = new InspectionResultVo(currentBusId, startInspectionTime, cardInfo);
        result.inspecotrId = currentInspectorInfo.id;
        result.resultCode = checkCodeResult(cardInfo);

        recordInspectionResult(result);
    }

    private void recordInspectionResult(InspectionResultVo result) {
        FileWriter writer = null;
        try {
            String folder = "../" + result.inspecotrId;
            File file = new File(folder);
            if (!file.exists()) {
                file.mkdirs();
            }

            writer = new FileWriter(folder + "//" + result.inspecotrId + "_" + result.inpectionTime + ".txt", true);

            writer.write(result.toString() + "\r\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    private String checkCodeResult(CardInfo cardInfo) {
        String checkCode = "R1";

        if (!currentBusId.equals(cardInfo.busId)) {
            checkCode = "R2";
        } else if ("F".equals(cardInfo.code)) {
            checkCode = "R3";
        } else {
            try {
                if (CardUtility.hourDiff(CardUtility.getCurrentDateTimeString(), cardInfo.boardTime) > 3) {
                    checkCode = "R4";
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return checkCode;
    }

    private void checkLogin(InspectorInfo inspecotor) {
        boolean result = false;
        String expectedPwd = inspectorMap.get(inspecotor.id);
        String encPwd = "";
        try {
            encPwd = CardUtility.passwordEncryption(inspecotor.pwd);
        } catch (NoSuchAlgorithmException e) {
            result = false;
        }

        result = expectedPwd.equals(encPwd);

        if (result) {
            currentInspectorInfo = inspecotor;
            System.out.println("LOGIN SUCCESS");

        } else {
            System.out.println("LOGIN FAIL");
        }

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
