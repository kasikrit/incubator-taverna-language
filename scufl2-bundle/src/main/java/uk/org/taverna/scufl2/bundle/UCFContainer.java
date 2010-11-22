package uk.org.taverna.scufl2.bundle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;

import org.w3c.dom.Document;

import uk.org.taverna.scufl2.bundle.impl.odfdom.pkg.OdfPackage;

public class UCFContainer {

	private static final Charset UTF_8 = Charset.forName("utf-8");
	public static final String MIME_BINARY = "application/octet-stream";
	public static final String MIME_TEXT_PLAIN = "text/plain";
	public static final String MIME_TEXT_XML = "text/xml";
	public static final String MIME_RDF = "application/rdf+xml";
	public static final String MIME_EPUB = "application/epub+zip";
	public static final String MIME_WORKFLOW_BUNDLE = "application/vnd.taverna.workflow-bundle";
	public static final String MIME_DATA_BUNDLE = "application/vnd.taverna.data-bundle";
	public static final String MIME_WORKFLOW_RUN_BUNDLE = "application/vnd.taverna.workflow-run-bundle";
	public static final String MIME_SERVICE_BUNDLE = "application/vnd.taverna.service-bundle";

	private static Charset ASCII = Charset.forName("ascii");
	private final OdfPackage odfPackage;

	public UCFContainer() throws Exception {
		odfPackage = OdfPackage.create();
		odfPackage.setMediaType(MIME_EPUB);
	}

	public UCFContainer(File containerFile) throws Exception {
		odfPackage = OdfPackage.loadPackage(containerFile);
	}

	public UCFContainer(InputStream inputStream) throws Exception {
		odfPackage = OdfPackage.loadPackage(inputStream);
	}

	public String getBundleMimeType() {
		return odfPackage.getMediaType();
	}

	public void setBundleMimeType(String mimeType) {
		if (mimeType == null || !mimeType.contains("/")) {
			throw new IllegalArgumentException("Invalid media type " + mimeType);
		}
		if (!ASCII.newEncoder().canEncode(mimeType)) {
			throw new IllegalArgumentException("Media type must be ASCII: "
					+ mimeType);
		}
		odfPackage.setMediaType(mimeType);
	}

	public void save(File bundleFile) throws IOException {
		File tempFile = File.createTempFile(bundleFile.getName(), ".tmp",
				bundleFile.getParentFile());

		try {
			odfPackage.save(tempFile);
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw new IOException("Could not save bundle to " + bundleFile, e);
		}
		tempFile.renameTo(bundleFile);
	}

	public void insert(String stringValue, String path, String mimeType)
			throws Exception {
		odfPackage.insert(stringValue.getBytes(UTF_8), path, mimeType);
	}

	public void insert(byte[] bytesValue, String path, String mimeType)
			throws Exception {
		odfPackage.insert(bytesValue, path, mimeType);
	}

	public void insert(Document document, String path, String mimeType)
			throws Exception {
		odfPackage.insert(document, path, mimeType);
	}

	public void insert(InputStream inputStream, String path, String mimeType)
			throws Exception {
		odfPackage.insert(inputStream, path, mimeType);
	}

	public void insert(URI uri, String path, String mimeType) throws Exception {
		odfPackage.insert(uri, path, mimeType);
	}

	public String getEntryAsString(String path) throws Exception {
		return new String(odfPackage.getBytes(path), UTF_8);
	}

	public byte[] getEntryAsBytes(String path) throws Exception {
		return odfPackage.getBytes(path);
	}

	public InputStream getEntryAsInputStream(String path) throws Exception {
		return odfPackage.getInputStream(path);
	}


}
