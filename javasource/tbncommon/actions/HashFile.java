// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package tbncommon.actions;

import org.apache.commons.codec.digest.DigestUtils;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;

public class HashFile extends CustomJavaAction<java.lang.String>
{
	private IMendixObject __file;
	private system.proxies.FileDocument file;

	public HashFile(IContext context, IMendixObject file)
	{
		super(context);
		this.__file = file;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		this.file = __file == null ? null : system.proxies.FileDocument.initialize(getContext(), __file);

		// BEGIN USER CODE
        try
        {
            return DigestUtils.md5Hex(Core.getFileDocumentContent(getContext(), __file)).toUpperCase();
        }
        catch (Exception e)
        {
            throw new com.mendix.systemwideinterfaces.MendixRuntimeException(e);
        }
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "HashFile";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
