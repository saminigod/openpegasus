//%LICENSE////////////////////////////////////////////////////////////////
//
// Licensed to The Open Group (TOG) under one or more contributor license
// agreements.  Refer to the OpenPegasusNOTICE.txt file distributed with
// this work for additional information regarding copyright ownership.
// Each contributor licenses this file to you under the OpenPegasus Open
// Source License; you may not use this file except in compliance with the
// License.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the "Software"),
// to deal in the Software without restriction, including without limitation
// the rights to use, copy, modify, merge, publish, distribute, sublicense,
// and/or sell copies of the Software, and to permit persons to whom the
// Software is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included
// in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
// IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
// CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
// TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
// SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
//////////////////////////////////////////////////////////////////////////
//
//%/////////////////////////////////////////////////////////////////////////////

/*
    This file defines the a structure and functions to manage the command
    line input parameters and options and communicate this information
    between the parsing mechanisms and the operation action functions.
    One
*/
#include <Pegasus/Common/Config.h>
#include <Pegasus/Common/Constants.h>
#include <Pegasus/Common/PegasusAssert.h>
#include <Pegasus/Common/FileSystem.h>
#include <Pegasus/Common/XmlWriter.h>
#include <Pegasus/Common/Tracer.h>
#include <Pegasus/Common/StringConversion.h>
#include <Pegasus/Common/ArrayInternal.h>

#include <Pegasus/Common/PegasusVersion.h>

#include <Pegasus/General/MofWriter.h>

#include "CIMCLIClient.h"

#include "ObjectBuilder.h"
#include "CIMCLIOutput.h"
#include "CIMCLIHelp.h"
#include "CIMCLIOptions.h"
#include "CIMCLICommon.h"

PEGASUS_USING_STD;
PEGASUS_NAMESPACE_BEGIN

/******************************************************************************
**
**             Option Structure object. Initialization and any other
**             used Function Definitions
**
******************************************************************************/

// Constructor - Instantiate the variables of the Option structure
OptionStruct::OptionStruct():
    isXmlOutput(false),

    deepInheritance(false),
    localOnly(true),
    includeQualifiersRequested(false),
    notIncludeQualifiersRequested(false),
    includeQualifiers(false),
    includeClassOrigin(false),

    verboseTest(false),
    summary(false),
    delay(0),
    trace(0),
    debug(false),
    sort(false),

    expectedCount(0),
    executeCountTest(false),
    repeat(0),
    time(false),
    termCondition(0),
    connectionTimeout(0),
    interactive(false),
    setRtnHostNames(false)
{
        // Initialize the option structure values
        location =  String();
#ifdef PEGASUS_HAS_SSL
        ssl = false;
        clientCert = String();
        clientKey = String();
#endif
        nameSpace = String();
        cimCmd = "unknown";
        targetObjectName = CIMObjectPath();

        outputFormatType = OUTPUT_MOF;

        user = String();
        password = String();

        assocClass = CIMName();
        resultClass = CIMName();
        role = String::EMPTY;
        resultRole = String();
        propertyList.clear();
        propertyName = String();
        methodName = CIMName("unknown");
        queryLanguage = "WQL";
        rtnHostSubstituteName = String();
}

/*
    Get the String for the current targetObject
*/
String OptionStruct::getTargetObjectNameStr()
{
    return(targetObjectName.toString());
}

/*
    get the class name for the targetObject for this operation
*/
CIMName OptionStruct::getTargetObjectNameClassName()
{
    return(targetObjectName.getClassName());
}

/*
    get the targetObjectName
*/
CIMObjectPath OptionStruct::getTargetObjectName()
{
    return(targetObjectName);
}

/*
    Return true of the target object is a class with no keys.
    Since there is no such thing as a no key class, this means that
    the user input only the classname.
*/
Boolean OptionStruct::targetObjectNameClassOnly()
{
    return(targetObjectName.getKeyBindings().size() == 0);
}

PEGASUS_NAMESPACE_END
// END_OF_FILE
