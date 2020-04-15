// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package communitycommons.proxies;

public enum StandardEncodings
{
	US_ASCII(new java.lang.String[][] { new java.lang.String[] { "en_US", "US-ASCII" } }),
	ISO_8859_1(new java.lang.String[][] { new java.lang.String[] { "en_US", "ISO-8859-1" } }),
	UTF_8(new java.lang.String[][] { new java.lang.String[] { "en_US", "UTF-8" } }),
	UTF_16BE(new java.lang.String[][] { new java.lang.String[] { "en_US", "UTF-16BE" } }),
	UTF_16LE(new java.lang.String[][] { new java.lang.String[] { "en_US", "UTF-16LE" } }),
	UTF_16(new java.lang.String[][] { new java.lang.String[] { "en_US", "UTF-16" } });

	private java.util.Map<java.lang.String, java.lang.String> captions;

	private StandardEncodings(java.lang.String[][] captionStrings)
	{
		this.captions = new java.util.HashMap<java.lang.String, java.lang.String>();
		for (java.lang.String[] captionString : captionStrings)
			captions.put(captionString[0], captionString[1]);
	}

	public java.lang.String getCaption(java.lang.String languageCode)
	{
		if (captions.containsKey(languageCode))
			return captions.get(languageCode);
		return captions.get("en_US");
	}

	public java.lang.String getCaption()
	{
		return captions.get("en_US");
	}
}
