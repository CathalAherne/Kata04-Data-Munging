import java.io.*;

/**
 * Created by Cathal on 22/01/2017.
 *
 * Relevant column Nums: Weather: {6,8,12,14}
 *                       Table: {43,45,50,52}
 */
public class calculateLowestRange {

    private File dataFile;
    private FileReader fr;
    private BufferedReader br;
    private int[] relevantColumnNumbers;
    private int dataIdColumnStartIndex;

    public calculateLowestRange(String fileName,int[] relevantColumnNumbers) throws FileNotFoundException{
        dataFile = new File(fileName);
        fr = new FileReader(dataFile);
        br = new BufferedReader(fr);
        this.relevantColumnNumbers = relevantColumnNumbers;
    }

    public String getLowestRange()throws IOException {
        String line;
        int lowestRange = 0;
        String dataID = "";
        int range = 0;
        String header = br.readLine();
        //Get dataIdColumn starting position depending on file
        if(header.charAt(2) == 'D'){
            this.dataIdColumnStartIndex = 2;
        }else{
            this.dataIdColumnStartIndex = 7;
        }
        //While not end of file
        while ((line = br.readLine()) != null) {
            //If line is not blank and not a header line
            if (line.length() != 0 && (Character.isDigit(line.charAt(relevantColumnNumbers[0])))) {
                range = (Integer.parseInt(line.substring(relevantColumnNumbers[0], relevantColumnNumbers[1]))
                        - (Integer.parseInt(line.substring(relevantColumnNumbers[2], relevantColumnNumbers[3]))));
            }   //If range is negative, convert to positive value
                if (range < 0) {
                    range = range + (-2 * range);
                }

                if ((range < lowestRange || lowestRange == 0) && line.length() != 0) {
                    lowestRange = range;
                    int endOfWord = line.indexOf(' ', dataIdColumnStartIndex);
                    dataID = line.substring(dataIdColumnStartIndex, endOfWord);
                }
        }
        return dataID;
    }
    public static void main(String[] args) throws IOException{
        int[] columnsWithRanges = {6,8,12,14};
        calculateLowestRange run = new calculateLowestRange("weather.dat",columnsWithRanges);
        System.out.println(run.getLowestRange());
    }
}
