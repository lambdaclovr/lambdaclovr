package org.spark.vision;

public enum FrameOptions {
    ALL,            // Extract all frames
    KEYFRAMES,      // Extract only Keyframes
    STEP,           // Extract frames based on specified step
    EXACT,          // Extract the exact frame number
    INTERVAL,       // Extract frames at an interval
    TIMESTAMP,      // Extract frame at a specific timestamp
    RANGE           // Extract a range of frames, either by frame number, or time
}
