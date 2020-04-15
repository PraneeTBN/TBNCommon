// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package tbncommon.actions;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;

/**
 * Get a match from a stream of data.
 * MineType
 * Ex. application/pdf;application/msword;text/plain;application/zip;image/jpeg;image/png
 */
public class IsMatchMimeType extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __File;
	private system.proxies.FileDocument File;
	private java.lang.String MimeType;

	public IsMatchMimeType(IContext context, IMendixObject File, java.lang.String MimeType)
	{
		super(context);
		this.__File = File;
		this.MimeType = MimeType;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.File = __File == null ? null : system.proxies.FileDocument.initialize(getContext(), __File);

		// BEGIN USER CODE
		InputStream is = null;
		InputStream ins = null;
		try {
			is = Core.getFileDocumentContent(getContext(), __File);
			boolean isVdo = this.isVideoType(is);
			
			if(isVdo) {
				return StringUtils.containsIgnoreCase(MimeType,"video/mp4")?true:false;
			}else {
				ins = Core.getFileDocumentContent(getContext(), __File);
				byte[] data = IOUtils.toByteArray(ins);
				MagicMatch match = Magic.getMagicMatch(data);
				
				Core.getLogger("MIME-TYPE").info("mime-type: " + match.getMimeType());
				
				
				return StringUtils.containsIgnoreCase(MimeType, match.getMimeType());
			}
		} catch (MagicMatchNotFoundException eNF) {
			return true;
		} catch (Exception e) {
			Core.getLogger("MIME-TYPE").error("mime-type: " +e);
			throw new com.mendix.systemwideinterfaces.MendixRuntimeException(e);
		} finally {
			if(ins!=null){
				ins.close();
			}
			is.close();
		}
			
		
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "IsMatchMimeType";
	}

	// BEGIN EXTRA CODE
    private String convertToHex(InputStream is) throws IOException {
		
        int bytesCounter = 0;
        int value = 0;
        StringBuilder sbHex = new StringBuilder();
        //StringBuilder sbText = new StringBuilder();
        StringBuilder sbResult = new StringBuilder();
        
        int i = 0;
        int max = 10000;

        while ((value = is.read()) != -1 && i++ < max) {
            //convert to hex value with "X" formatter
            sbHex.append(String.format("%02X ", value));

            //if 16 bytes are read, reset the counter, 
            //clear the StringBuilder for formatting purpose only.
            if (bytesCounter == 15) {
                sbResult.append(sbHex).append("\n");
                sbHex.setLength(0);
                bytesCounter = 0;
                break;
            } else {
                bytesCounter++;
            }
        }

        //if still got content
        if (bytesCounter != 0) {
            sbResult.append(sbHex).append("\n");
        }
 
        return sbResult.toString();
   }

   private boolean isVideoType(InputStream is) throws IOException {
	    String hex=convertToHex(is);
		boolean result = false;
		
		if(StringUtils.containsIgnoreCase(hex,"00 00 00 18 66 74 79 70 6D 70 34 32")){ 
		    result = true;
		}
		return result;
   }
	// END EXTRA CODE
}
