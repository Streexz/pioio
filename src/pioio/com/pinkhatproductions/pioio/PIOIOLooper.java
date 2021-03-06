/*
  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation, version 3.0.
  (http://www.gnu.org/licenses/lgpl-3.0.html)

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA
  
  Contributors:
      PinkHatSpike - spike@pinkhatproductions.com
*/
 
package com.pinkhatproductions.pioio;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import ioio.lib.api.IOIO;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.spi.Log;

import processing.core.PApplet;

public class PIOIOLooper extends BaseIOIOLooper {
    private static final String TAG = "PIOIOLooper";
    
    PApplet _applet;
    Method _ioioSetup;
    Method _ioioLoop;

    public PIOIOLooper(PApplet applet) {
        super();
        
        _applet = applet;

        Class[] cArg = new Class[1];
        cArg[0] = IOIO.class;
        try {
            _ioioSetup = _applet.getClass().getMethod("ioioSetup", cArg);
        }
        catch(NoSuchMethodException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException("could not find defined function 'void ioioSetup(IOIO ioio)' [" + e.toString() + "]", e);
        }
        catch(NullPointerException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
        catch(SecurityException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
        
        try {
            _ioioLoop = _applet.getClass().getMethod("ioioLoop", cArg);
        }
        catch(NoSuchMethodException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException("could not find defined function 'void ioioLoop(IOIO ioio)' [" + e.toString() + "]", e);
        }
        catch(NullPointerException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
        catch(SecurityException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setup() throws ConnectionLostException {
        try {
            _ioioSetup.invoke(_applet, ioio_);
        }
        catch (IllegalArgumentException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
        catch (InvocationTargetException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loop() throws ConnectionLostException {
        try {
            _ioioLoop.invoke(_applet, ioio_);
        }
        catch (IllegalArgumentException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
        catch (InvocationTargetException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
    }
}