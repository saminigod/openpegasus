#//%LICENSE////////////////////////////////////////////////////////////////
#//
#// Licensed to The Open Group (TOG) under one or more contributor license
#// agreements.  Refer to the OpenPegasusNOTICE.txt file distributed with
#// this work for additional information regarding copyright ownership.
#// Each contributor licenses this file to you under the OpenPegasus Open
#// Source License; you may not use this file except in compliance with the
#// License.
#//
#// Permission is hereby granted, free of charge, to any person obtaining a
#// copy of this software and associated documentation files (the "Software"),
#// to deal in the Software without restriction, including without limitation
#// the rights to use, copy, modify, merge, publish, distribute, sublicense,
#// and/or sell copies of the Software, and to permit persons to whom the
#// Software is furnished to do so, subject to the following conditions:
#//
#// The above copyright notice and this permission notice shall be included
#// in all copies or substantial portions of the Software.
#//
#// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
#// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
#// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
#// IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
#// CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
#// TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
#// SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
#//
#//////////////////////////////////////////////////////////////////////////
ROOT = ../../..
DIR = Pegasus/Client
include $(ROOT)/mak/config.mak

ifeq ($(PEGASUS_ENABLE_SLP),true)
    FLAGS += -DPEGASUS_SLP_CLIENT_INTERFACE_WRAPPER
endif

EXTRA_INCLUDES = $(SYS_INCLUDES)

LOCAL_DEFINES = -DPEGASUS_CLIENT_INTERNAL -DPEGASUS_INTERNALONLY

ifeq ($(OS_TYPE),vms)
    STATIC=1
endif

LIBRARY = pegclient

LIBRARIES = \
    pegcommon

ifeq ($(PEGASUS_ENABLE_SLP),true)
    ifneq ($(OS_TYPE),windows)
        LIBRARIES += pegslp_client
    else
        SYS_LIBS = $(PEGASUS_HOME)/lib/pegslp_client.lib
    endif
endif

PRE_DEPEND_INCLUDES = -I./depends

SOURCES = \
    ClientPerfDataStore.cpp \
    ClientOpPerformanceDataHandler.cpp \
    CIMClientRep.cpp \
    CIMClient.cpp \
    CIMOperationRequestEncoder.cpp \
    CIMOperationResponseDecoder.cpp \
    ClientAuthenticator.cpp \
    CIMClientException.cpp

SOURCES_SLP = \
    CIMServerDiscovery.cpp

ifeq ($(PEGASUS_ENABLE_SLP),true)
    SOURCES += $(SOURCES_SLP)
endif

include $(ROOT)/mak/dynamic-library.mak

ifeq ($(OS_TYPE),windows)
    SYS_LIBS += ws2_32.lib advapi32.lib
endif
