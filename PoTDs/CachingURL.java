

public class CachingURL {
	private java.net.URL upstream;
	private java.io.File cache;
	static private java.util.Map<String, byte[]> loaded = new java.util.TreeMap<String, byte[]>();
	
	public CachingURL(java.lang.String path) throws java.net.MalformedURLException {
		this.upstream = new java.net.URL(path);
		java.lang.String fn = this.upstream.getFile();
		this.cache = new java.io.File("cached_url_"+fn.replace('/','_'));
	}
	
	public java.io.InputStream openStream() throws java.io.IOException {
		if (loaded.containsKey(cache.getPath())) {
			return new java.io.ByteArrayInputStream(loaded.get(cache.getPath()));
		}
		if (!cache.exists()) {
			java.io.InputStream fromWeb = upstream.openStream();
			java.io.FileOutputStream toFile = new java.io.FileOutputStream(cache);
			byte[] data = new byte[1024];
			int read = 0;
			do {
				read = fromWeb.read(data);
				if (read > 0) toFile.write(data,0,read);
			} while (read >= 0);
			toFile.close();
			fromWeb.close();
		}
		byte[] backend = new byte[(int)cache.length()];
		java.io.InputStream is = new java.io.FileInputStream(cache);
		is.read(backend);
		is.close();
		loaded.put(cache.getPath(), backend);
		return new java.io.ByteArrayInputStream(backend);
	}
}
