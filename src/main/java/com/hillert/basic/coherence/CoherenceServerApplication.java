/*
 * Copyright (c) 2020, Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */
package com.hillert.basic.coherence;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.Scanner;

import com.tangosol.net.Coherence;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class CoherenceServerApplication {

	public static void main(String[] args) {
		Instant startTime = Instant.now();
		final Coherence coherence = Coherence.clusterMember();
		coherence.start().join();

		Map map = coherence.getSession().getCache("berlin-kona");
		map.put("test.foo", "bar");

		System.out.println("Server starting...");
		Instant endTime = Instant.now();
		System.out.println("Server started in " + Duration.between(startTime, endTime).toMillis() + "ms.");

		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		System.out.println("Exiting...");
		coherence.close();
		System.exit(0);
	}
}
