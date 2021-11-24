export interface GeanAppodealPlugin {
  showInterstitial(options: { value: string }): Promise<{ value: string }>;
}
