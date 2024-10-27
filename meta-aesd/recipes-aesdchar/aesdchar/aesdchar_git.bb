# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module
inherit logging

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-bennowotny.git;protocol=ssh;branch=master \
           file://load-aesdchar \
           file://0001-enable-debug-to-help-fix-bug.patch;patchdir=.. \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "7fc82f1a7dca1c4ef28cde8f8fb3f8b7ba1276bb"

S = "${WORKDIR}/git/aesd-char-driver"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-aesdchar"
EXTRA_OEMAKE:append:task-install = "  -C ${STAGING_KERNEL_DIR} M=${S}"
EXTRA_OEMAKE += " KERNELDIR=${STAGING_KERNEL_DIR}"

inherit update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "load-aesdchar"

FILES:${PN} += "${sysconfdir}/init.d/load-aesdchar"

do_install:append(){
  install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/load-aesdchar ${D}${sysconfdir}/init.d
}
