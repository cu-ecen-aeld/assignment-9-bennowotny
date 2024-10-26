FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://remove-default-faulty.cfg"
# SRC_URI += "file://kernel-hacking.cfg" # used to set up kernel hacking/leak testing
