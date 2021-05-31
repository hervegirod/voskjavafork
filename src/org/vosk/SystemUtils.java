/*------------------------------------------------------------------------------
 * Copyright (C) 2021 Herve Girod
 *
 * Distributable under the terms of either the Apache License (Version 2.0) or
 * the GNU Lesser General Public License, as specified in the COPYING file.
 ------------------------------------------------------------------------------*/
package org.vosk;

/**
 * This class provides several System utilities.
 */
class SystemUtils {
   private static final short OS_UNDEFINED = -1;
   /**
    * The type for OS which are neither Mac OS X, Windows, Linux, or Unix.
    */
   public static final short OS_OTHER = 0;
   /**
    * The type for MAC OS X OS.
    */
   public static final short OS_MACOSX = 1;
   /**
    * The type for Windows OS.
    */
   public static final short OS_WINDOWS = 2;
   /**
    * The type for Linux OS.
    */
   public static final short OS_LINUX = 3;
   /**
    * The type for Unix OS.
    */
   public static final short OS_UNIX = 4;
   private static final short ARCH_UNDEFINED = -1;
   /**
    * The type for unknown processor architectures.
    */
   public static final short ARCH_OTHER = 0;
   /**
    * The type for 32 bits x86 architectures.
    */
   public static final short ARCH_X86_32 = 1;
   /**
    * The type for 64 bits x86 architectures.
    */
   public static final short ARCH_X86_64 = 2;
   /**
    * The type for 32 bits PPC architectures.
    */
   public static final short ARCH_PPC_32 = 3;
   /**
    * The type for 64 bits PPC architectures.
    */
   public static final short ARCH_PPC_64 = 4;
   /**
    * The type for 32 bits SPARC architectures.
    */
   public static final short ARCH_SPARC_32 = 5;
   /**
    * The type for 64 bits SPARC architectures.
    */
   public static final short ARCH_SPARC_64 = 6;
   /**
    * The type for 32 bits ARM architectures.
    */
   public static final short ARCH_ARM_32 = 7;
   /**
    * The type for 64 bits ARM architectures.
    */
   public static final short ARCH_ARM_64 = 8;
   private static short osType = OS_UNDEFINED;
   private static short archType = ARCH_UNDEFINED;

   private SystemUtils() {
   }

   /**
    * Return the OS type. The supported OS are:
    * <ul>
    * <li>{@link #OS_MACOSX}</li>
    * <li>{@link #OS_WINDOWS}</li>
    * <li>{@link #OS_LINUX}</li>
    * <li>{@link #OS_UNIX} for Unix systems (AIX, SunOS, hp-ux)</li>
    * <li>{@link #OS_OTHER} for unknown OS types</li>
    * </ul>
    *
    * @return the OS type
    */
   public static final short getPlatformType() {
      if (osType == OS_UNDEFINED) {
         String osName = System.getProperty("os.name").toLowerCase();
         if (osName.startsWith("mac os x")) {
            osType = OS_MACOSX;
         } else if (osName.startsWith("windows")) {
            osType = OS_WINDOWS;
         } else if (osName.startsWith("linux")) {
            osType = OS_LINUX;
         } else if (osName.startsWith("unix")) {
            osType = OS_UNIX;
         } else if (osName.startsWith("aix")) {
            osType = OS_UNIX;
         } else if (osName.startsWith("sunos")) {
            osType = OS_UNIX;
         } else if (osName.startsWith("hp-ux")) {
            osType = OS_UNIX;
         } else {
            osType = OS_OTHER;
         }
         return osType;
      } else {
         return osType;
      }
   }

   /**
    * Return the JVM Platform architecture. Note that a 64 bits platform with a 32 bits JVM will be detected as 32 bits.
    * <ul>
    * <li>{@link #ARCH_X86_32} and {@link #ARCH_X86_64} for x86 architectures</li>
    * <li>{@link #ARCH_PPC_32} and {@link #ARCH_PPC_64} for PPC architectures</li>
    * <li>{@link #ARCH_SPARC_32} and {@link #ARCH_SPARC_64} for SPARC architectures</li>
    * <li>{@link #ARCH_ARM_32} and {@link #ARCH_ARM_64} for ARM architectures</li>
    * <li>{@link #ARCH_OTHER} for unknown architectures</li>
    * </ul>
    *
    * @return the JVM Platform architecture
    */
   static final short getArchitecture() {
      if (archType == ARCH_UNDEFINED) {
         String osName = System.getProperty("os.arch").toLowerCase();
         if (osName.equals("x86") || osName.equals("ia32") || osName.equals("i386") || osName.equals("i486") || osName.equals("i586")
            || osName.equals("i686") || osName.equals("x8632") || osName.equals("x32")) {
            archType = ARCH_X86_32;
         } else if (osName.equals("x86_64") || osName.equals("ia64") || osName.equals("amd64") || osName.equals("x8664")
            || osName.equals("itanium64") || osName.equals("x64")) {
            archType = ARCH_X86_64;
         } else if (osName.equals("powerpc") || osName.equals("ppc")) {
            archType = ARCH_PPC_32;
         } else if (osName.equals("ppc64")) {
            archType = ARCH_PPC_64;
         } else if (osName.equals("sparc") || osName.equals("sparc32")) {
            archType = ARCH_SPARC_32;
         } else if (osName.equals("sparcv9") || osName.equals("sparc64")) {
            archType = ARCH_SPARC_64;
         } else if (osName.equals("arm") || osName.equals("arm32")) {
            archType = ARCH_ARM_32;
         } else if (osName.equals("aarch_64")) {
            archType = ARCH_ARM_64;
         } else {
            archType = ARCH_OTHER;
         }
      }
      return archType;
   }

   /**
    * Return true if the JVM Platform architecture is detected as 64 bits.
    * Note that a 64 bits platform with a 32 bits JVM will be detected as 32 bits.
    *
    * @return true if the JVM Platform architecture is detected as 64 bits
    */
   static final boolean is64Bits() {
      getArchitecture();
      return archType == ARCH_X86_64 || archType == ARCH_PPC_64 || archType == ARCH_SPARC_64 || archType == ARCH_ARM_64;
   }

   /**
    * Return true if the JVM Platform architecture is detected as 32 bits.
    * Note that a 64 bits platform with a 32 bits JVM will be detected as 32 bits.
    *
    * @return true if the JVM Platform architecture is detected as 32 bits
    */
   static final boolean is32Bits() {
      getArchitecture();
      return archType == ARCH_X86_32 || archType == ARCH_PPC_32 || archType == ARCH_SPARC_32 || archType == ARCH_ARM_32;
   }

   /**
    * Return the platform type.
    *
    * @return the platform type
    */
   static short getPlatform() {
      getPlatformType();
      return osType;
   }
}
