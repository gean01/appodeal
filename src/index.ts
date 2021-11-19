import { registerPlugin } from '@capacitor/core';

import type { GeanAppodealPlugin } from './definitions';

const GeanAppodeal = registerPlugin<GeanAppodealPlugin>('GeanAppodeal', {
  web: () => import('./web').then(m => new m.GeanAppodealWeb()),
});

export * from './definitions';
export { GeanAppodeal };
