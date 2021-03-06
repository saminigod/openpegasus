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
// Author: Adrian Schuur schuur@deibm.com
//
// Modified By: Mark Hamzy, hamzy@us.ibm.com
//
//
// This code is based on TestPropertyTypes.cpp
//
// Original Author: Yi Zhou (yi_zhou@hp.com)
//
// Original Modified By: Roger Kumpf, Hewlett-Packard Company (roger_kumpf@hp.com)
//                       Sushma Fernandes, Hewlett-Packard Company
//                         (sushma_fernandes@hp.com)
//                       Carol Ann Krug Graves, Hewlett-Packard Company
//                        (carolann_graves@hp.com)
//
//%/////////////////////////////////////////////////////////////////////////////
package Instances;

import java.util.Vector;
import org.pegasus.jmpi.CIMClass;
import org.pegasus.jmpi.CIMException;
import org.pegasus.jmpi.CIMInstance;
import org.pegasus.jmpi.CIMOMHandle;
import org.pegasus.jmpi.CIMObjectPath;
import org.pegasus.jmpi.CIMValue;
import org.pegasus.jmpi.InstanceProvider2;
import org.pegasus.jmpi.MethodProvider2;
import org.pegasus.jmpi.OperationContext;

public class JMPIExpInstanceProvider
             implements InstanceProvider2,
                        MethodProvider2
{
   public void initialize (CIMOMHandle ch)
      throws CIMException
   {
      if (cip != null)
      {
         cip.cleanup ();
      }

      cip = new CommonInstanceProvider ("JMPIExpInstanceProvider",
                                        "root/SampleProvider",
                                        "JMPIExpInstance_TestElement",
                                        "JMPIExpInstance_TestPropertyTypes");

      if (cip != null)
      {
         cip.initialize (ch);
      }
   }

   public void cleanup ()
      throws CIMException
   {
      if (cip != null)
      {
         cip.cleanup ();

         cip = null;
      }
   }

   public CIMObjectPath createInstance (OperationContext oc,
                                        CIMObjectPath    cop,
                                        CIMInstance      cimInstance)
      throws CIMException
   {
      if (cip != null)
      {
         return cip.createInstance (oc,
                                    cop,
                                    cimInstance);
      }
      else
      {
         return null;
      }
   }

   public CIMInstance getInstance (OperationContext oc,
                                   CIMObjectPath    cop,
                                   CIMClass         cimClass,
                                   boolean          includeQualifiers,
                                   boolean          includeClassOrigin,
                                   String           propertyList[])
      throws CIMException
   {
      if (cip != null)
      {
         return cip.getInstance (oc,
                                 cop,
                                 cimClass,
                                 includeQualifiers,
                                 includeClassOrigin,
                                 true,               // localOnly
                                 propertyList);
      }
      else
      {
         return null;
      }
   }

   public void setInstance (OperationContext oc,
                            CIMObjectPath    cop,
                            CIMInstance      cimInstance)
      throws CIMException
   {
      if (cip != null)
      {
         cip.setInstance (oc,
                          cop,
                          cimInstance,
                          true,        // includeQualifiers,
                          null);       // propertyList);
      }
   }

   public void deleteInstance (OperationContext oc,
                               CIMObjectPath    cop)
      throws CIMException
   {
      if (cip != null)
      {
         cip.deleteInstance (oc,
                             cop);
      }
   }

   public Vector enumerateInstanceNames (OperationContext oc,
                                         CIMObjectPath    cop,
                                         CIMClass         cimClass)
      throws CIMException
   {
      if (cip != null)
      {
         return cip.enumerateInstanceNames (oc,
                                            cop,
                                            false,      // deepInheritance,
                                            cimClass);
      }
      else
      {
         return null;
      }
   }

   public Vector enumerateInstances (OperationContext oc,
                                     CIMObjectPath    cop,
                                     CIMClass         cimClass,
                                     boolean          includeQualifiers,
                                     boolean          includeClassOrigin,
                                     String           propertyList[])
      throws CIMException
   {
      if (cip != null)
      {
         return cip.enumerateInstances (oc,
                                        cop,
                                        cimClass,
                                        false,              // deepInheritance,
                                        true,               // localOnly,
                                        includeQualifiers,
                                        includeClassOrigin,
                                        propertyList);
      }
      else
      {
         return null;
      }
   }

   public Vector execQuery (OperationContext oc,
                            CIMObjectPath    cop,
                            CIMClass         cimClass,
                            String           queryStatement,
                            String           queryLanguage)
      throws CIMException
   {
      if (cip != null)
      {
         return cip.execQuery (oc,
                               cop,
                               queryStatement,
                               queryLanguage,
                               cimClass);
      }
      else
      {
         return null;
      }
   }

   public CIMValue invokeMethod (OperationContext oc,
                                 CIMObjectPath    cop,
                                 String           methodName,
                                 Vector           in,
                                 Vector           out)
      throws CIMException
   {
      if (cip != null)
      {
         return cip.invokeMethod (oc,
                                  cop,
                                  methodName,
                                  in,
                                  out);
      }
      else
      {
         return null;
      }
   }

   private CommonInstanceProvider cip = null;
}
