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

import com.pinkhatproductions.pioio.PIOIOLooperProvider;
import ioio.lib.util.android.IOIOAndroidApplicationHelper;
import ioio.lib.util.IOIOLooperProvider;
import ioio.lib.android.bluetooth.*;
import ioio.lib.android.accessory.*;
import android.content.ContextWrapper;

import processing.core.PApplet;

import java.util.Collection;
import ioio.lib.spi.IOIOConnectionBootstrap;
import ioio.lib.util.android.ContextWrapperDependent;
import ioio.lib.util.IOIOConnectionRegistry;

public class PIOIOManager extends IOIOAndroidApplicationHelper {
    private static final String TAG = "PIOIOManagerAndroid";
    
    public PIOIOManager(PApplet wrapper, IOIOLooperProvider provider) {
        super(wrapper, provider);
        
        Collection<IOIOConnectionBootstrap> bootstraps_ = IOIOConnectionRegistry.getBootstraps();
        for (IOIOConnectionBootstrap bootstrap : bootstraps_) {
            if (bootstrap instanceof ContextWrapperDependent) {
                ((ContextWrapperDependent) bootstrap).onCreate(wrapper);
            }
        }
    }
    
    public PIOIOManager(PApplet applet) {
        this(applet, new PIOIOLooperProvider(applet));
    }
}
