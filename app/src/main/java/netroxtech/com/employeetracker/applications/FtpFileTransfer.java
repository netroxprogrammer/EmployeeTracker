package netroxtech.com.employeetracker.applications;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

import netroxtech.com.employeetracker.utils.Constants;
import netroxtech.com.employeetracker.utils.InitilizeSharePref;

/**
 * Created by mac on 11/6/2016.
 */
public class FtpFileTransfer extends AsyncTask<Void,Void,Void> {
    private Context  context;
    private FTPClient ftpClient;
    InitilizeSharePref sharePref;
    public FtpFileTransfer(Context context){
        this.context = context;
        ftpClient = new FTPClient();
        sharePref = new InitilizeSharePref(context);
    }
    @Override
    protected Void doInBackground(Void... voids) {

        return null;
    }

    /**
     * Ftp Host Connection Function  return  Connection true or  false
     * @return
     */

    public boolean isFTPConnect() throws IOException{
        // set ftp host Name and port
        ftpClient.connect(sharePref.getSingleData(Constants.FTPHOSTADDRESS, Constants.FTPPORTADDRESS));
        // set login userName and password for Ftp Connection
        boolean  status = ftpClient.login(Constants.FTPUSERNAME, Constants.FTPPASSWORD);
        if(FTPReply.isPositiveCompletion(ftpClient.getReply())){
            return  true;
        }
        return  false;
    }
    public void TransferFiles() throws  IOException{
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

    }
}
