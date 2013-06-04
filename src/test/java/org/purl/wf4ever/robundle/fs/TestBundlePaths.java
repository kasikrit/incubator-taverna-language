package org.purl.wf4ever.robundle.fs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;

import org.junit.Test;

public class TestBundlePaths extends Helper {

	@Test
	public void endsWith() throws Exception {
	    Path root = fs.getRootDirectory();
	    Path barBazAbs = root.resolve("bar/baz");
	    System.out.println(barBazAbs);
	    Path barBaz = root.relativize(barBazAbs);
	    assertEquals("bar/baz", barBaz.toString());
	    assertTrue(barBaz.endsWith("bar/baz"));
	    assertFalse(barBaz.endsWith("bar/../bar/baz"));
	    Path climber = barBaz.resolve("../baz");
	    assertEquals("bar/baz/../baz", climber.toString());
	    assertTrue(climber.endsWith("../baz"));
	    assertFalse(climber.endsWith("bar/baz"));
	    Path climberNorm = climber.normalize();
	    assertFalse(climberNorm.endsWith("../baz"));
	    assertTrue(climberNorm.endsWith("bar/baz"));		
	}
	
	@Test
	public void parent() throws Exception {
		Path root = fs.getRootDirectory();
		assertNull(root.getParent());
	}
	
}
