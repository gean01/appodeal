import { WebPlugin } from '@capacitor/core';

import type { GeanAppodealPlugin } from './definitions';

export class GeanAppodealWeb extends WebPlugin implements GeanAppodealPlugin {
  async showInterstitial(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
