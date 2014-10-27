package com.codename1.admob;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.util.Base64;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdMobNativeImpl implements com.codename1.admob.AdMobNative {

    private boolean isLoaded;
    
    public void init(String param) {
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void showAd() {
        if(!isLoaded){
            return;
        }
        final Form current = Display.getInstance().getCurrent();
        Form f = new Form("");
        f.setTransitionInAnimator(CommonTransitions.createEmpty());
        f.setTransitionOutAnimator(CommonTransitions.createEmpty());
        f.setLayout(new BorderLayout());
        Container north = new Container();
        north.addComponent(new Button(new Command("X") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                current.showBack();
            }

        }));
        f.addComponent(BorderLayout.NORTH, north);
        try {
            byte [] image = Base64.decode(base64Image.getBytes());
            Image i = Image.createImage(image, 0, image.length);            
            Button ad = new Button(i);
            ad.setUIID("Container");
            ad.getUnselectedStyle().setAlignment(com.codename1.ui.Component.CENTER);
            ad.getSelectedStyle().setAlignment(com.codename1.ui.Component.CENTER);
            ad.getPressedStyle().setAlignment(com.codename1.ui.Component.CENTER);
            ad.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    Display.getInstance().execute("http://www.codenameone.com");
                }
            });
            f.addComponent(BorderLayout.CENTER, ad);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        f.show();
        isLoaded = false;
    }

    public boolean loadAd() {
        try {
            Thread.sleep(500);
        } catch (Exception ex) {
        }
        isLoaded = true;
        return true;
    }

    public boolean isSupported() {
        return true;
    }

    private String base64Image = "iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAABmJLR0QA/wD/AP+gvaeTAAAACXBI\n"
            + "WXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3gobESAdwi6e/AAAGo5JREFUeNrtnXl8FFW693/nOae6\n"
            + "eksn6c5O2BxHxnvHcTZEL76jOKKO4jK+o/fq1VFcUAgQCDuCJIR9U1TEi8soo87MqzN3XO51Ztxx\n"
            + "G3UUBx0FRCCRCFkgS3ent6pz3j+qutPBEQPphAR5+NSHpDqf6qr61vN7nvOcpZhSCset7xgdvwXH\n"
            + "gRy340D6j4lv2gXfNm+iRsSHaoJf4NC0KtM0N9w2b9WsvnJ+7JsS1KfMvMnhdbgu4pwu83k9o7M9\n"
            + "rmKvx4XGA60IR2I3zp674qHjQHrB5s6aoJNDlBGjm/w+z+CCQLbbqWvgRGAADFNiT0PzvoRhjJk2\n"
            + "Y8l7x4H0gM2eM9FBhCLB6VbOeXl2lsc9oCAXuqbhn11vNJ5AU0vwbQDnlU2uajseQzJkM2bemqcY\n"
            + "znA59Cu9bv2K7Cy3np/jg+7QIKXEVz18ToeGbI97RDgauxPADcc9pJs2bfqtQxjHzU6H44L8XN93\n"
            + "/T6vw+3S4RAc8jCury0cRdwwF914y9z5x4EciUfMuvVkKFTpDu2ikgK/s9CfTULwbh0z1B4LSymv\n"
            + "v/am2U8eB9IFq5g+Lp8x9mOnQ6vQde3cwkAOigI54IzBzMS1KIZIPLFdKTXmquunf3ocyD+xqRXj\n"
            + "NE3jpxqGebHX47okJ8vzfX+2Fzk+DzgRpJSZuBVgjIGIYJoSCcP4i1LqoiuurTCOB/WDTNP4dwE8\n"
            + "M2zogCK/zwNN00AEKIUMwLBAMMZAjAGMoGkcYDgvHk88COC6b3TphI0eS2z0ddls9NhTk/tWLF+/\n"
            + "GVBPtIUjcDp1MGbByIBPpMEggPGUlzh1F4jol797ZNXkb6RksXOucQL8uxD0M7euj09IWZxIGCPV\n"
            + "Xx56s6PsMemNb5UW/lsgJysDMAiMmAWFKAWGEaVAAUBrsO1APB6/9D9vmPX6N8JD2Dn/6WA/vX4s\n"
            + "uPZETo73zycU5S38TmlB8UnFeQBjj7JzbyhM+/Ox+/a31sUTRje9wobBOmAQYyCy/rc+I3AukOXN\n"
            + "8nMh7tn4wFL/MQ2EnXt9DjtvbCWEvjuQk7XheyeUjhk2oMBfkJ0FhxDwuV0YWuAfCsi17MKfMwBY\n"
            + "vOju7fGEsWBvU0vCfoAP7zsZpXkDgYiDiEBEYJzAGLf2M7L3MzidTnjdnlMF8UePOcli59/ggVKD\n"
            + "SIgJguhmv8etlwR8cGkazK8Iztv2NiWa20LXqb889JvkvkXV0/44sCjv0lyfB109f8Y6ZAm2l3Bi\n"
            + "gB0zAMtDAAtEEloSYuP+RoRCocXX3TxnXr8Hws4fOwCKnc8d4jKfUx+T7XKyQJYHusahpMKhziAS\n"
            + "T2Db3qbaaDxxpvrTA58DwLLF0/OF5nh3YKF/sMvp+Boo1g1njKWyMlPKOsGpUNddIgkiFUNSckad\n"
            + "4gvA8MW+OjMYCl1z0/j5v+2XQNiFNw8D1FTi/Lwin7c04HVpTqFBcNblLIkxoL4thJ1NLZvUsxvO\n"
            + "Su5fvXLuaR6n/nZBwAdO9FXRAoys7zJNE0qpPVKphQBeVkqtcLvcP9d1Pa0NYgFIehClB3tGSJgG\n"
            + "Pv+8dk97NDp63IT5W/sNEHbxLT8CUOXgdGFxdhYryvLiSDQ/FeiI8Mm+RjSHI9Xq2Q23J/ffc+ft\n"
            + "03xZ7lXZHteXUMC+kYZhwpSyWQGLoNR9V1xb0Q4Av3145UnE+cs5vpwSTRP231OnoG+lwrak2YG/\n"
            + "NdiGurq6tyLx+FkTJi1I9Fkg7OJxQ8HYSIfgE5xCnJHvdSPf4wYxdlgFvq+saEDh7180tkdj8YvU\n"
            + "sxteAYD1d1f6OOdP+n3u0ZoQKRgKDKaUMAxzG6B+q5Ra/u/XTY8cfMzfPLzySiHEbwK5AeJcAAxg\n"
            + "xK1MCwywoXR4CQdxwp4v9qChoeGBcWXzb+5zQNhlt/4ASt3ocjjODbidw7J1HVm6I2MgOp56oC2e\n"
            + "wLbGA381TGOMeuq/9gPA/fdWn6o7tFd9Hlc2YHU4SaU+lFL+FwF/vPK66XWHOu5jv1ox1+1yLw7k\n"
            + "BjplYCwt/U1lZaxD0nbs2oGGpobZk8qrl/cJIPyyW9yCaK0uxJUDfFm+LF23NLiHE4S9wRDqg6Hl\n"
            + "kd/fOzu5b+P9y67VNL6RMbYHwBzF2DNX/XJaa1eO9+sHl+mM0cY8f96VWd4ssGTmZXtE6udO6TLB\n"
            + "lBIf/ePDlrZg21WTplb/6agByb+6jKIx9TMh+GM5Lld2YZYXBKC3kmepFHYfaEYknjin7cl1L6c9\n"
            + "6RfHDPXcDTfPOuxW48MblnxbCPHcwNJB33I49A6JAgPjloQlU+f0eBJub8eH/9jyUTQa/dnkikV7\n"
            + "eh2I7xdluYKo2qM7yvI8Hrh1DVL2YjvGTrsOhNvREAy2KKVOaH5iXXMmjv3AfdVXeFyex4YMHqpx\n"
            + "smpanWMIdSqrMLtBuaduDz79bPvTponLyyuqzF5rqQeunJjv0MT/5Hk9ZQOyfXBpoldhEGOIGga+\n"
            + "aG1DSyTyHhgbD6h4po5/063zn2hvDy+v+6IOxO0WfBICdWRdyd+tlJgweNBgFBcNuMQ0E/f0mocU\n"
            + "XTWphBF/M+DxDPbpOnq7HGlIieZwO8KJeC2kmhI3zGf2P3FPj/RTPHjfkv8tKS75WWFBUUfLniVh\n"
            + "pLXek7UvsjKvV159OdHa2jpx2qylG7rz/V/bH1J89eQBGhdP57rdg7OczoxmTodOcwHDNBGOxRLB\n"
            + "WHSblOrevY+vXd/T3xuNR2+ob6h/0+fLHur1eDvaJ6mAzjrHEiLEYglEou0aCapcvWrue9OmH/lw\n"
            + "okNKVsnVU4XGxZ05bvcPvbreKzAYgFgigeb2djSGQptaIpGbTWme+UUvwACAsskL94XCwam7ana1\n"
            + "W41S/qVCZLLwSETgQsPHWz+CaZpgjBUDWLds6eysHgGicczwOBy/8PaCTDHGkDBNNIVCaG4Pv9Me\n"
            + "i/3UNOUlex9f+8jex+9u7U2JnDhl0VOtra3rt+3YBiEcnUB0eAcH5wKNTQ1oaKi3a2UKDBjBOXs8\n"
            + "4zFk8DUVpwjBt+R7vanOmp4yU0q0RaNGJJGoUVLNrn10zVEZ8XGwrVu74J0TTxw2fMigIamHBslg\n"
            + "bwN4f/O7qPuiDqkGmFQwlIKSsmrm7OWVGfGQrAuvJ+K0IcfptEvTPWNx00RbNCobQ6FN0Xj8lpqN\n"
            + "q0/sKzDsZOKi3TW7atraWi3vIAJPeQtHU1MT6vbaMBRSlWtiABibuXTpjEsyAiQvL3CFS9N+8FVD\n"
            + "L7srTXHDQGskgtb29ufD0ehl0lSX7v71mofQx6x8anVjKBy69ZPtW8PqoHgCAB98+D6UPSJSKYXU\n"
            + "PwUwwMXA7lyyeMagbgEZel2Fi4iu8DgceiZRsFT6GkZrNLolFo+PNqUcs/vXa56peXR1C/qoTZux\n"
            + "9E/19Xvv/njrx1LTHCDicDh0vLf5XYTDIevKlFX8BDoPvmCMDQXweLeAEGiIU9NG8QxKlSklgtFo\n"
            + "rKW9/f2EIa/Y8asVp362cfULuzaujqMfWLbHNX/b9o/frqndDV3XUbunBjW1u5Cs3B0MI6kqVj2M\n"
            + "jVxUPe3eIwbCic5wcO7PVCCPJhJoi0Q3R+LxsWY0dvqOR1Y8iX5m4yZVGVzT/n3zB+/V7di5Ax9u\n"
            + "2WyX+TskytpUCoZUClIqWLVKNn7hwooJR5RlnXzDrP/2OJ2XaZx3+0Ii8TiiicTDylDTt21csR/9\n"
            + "3FYunz2Sc7EJSlISRLpHJH9Wae6S/ChhGvWmiQsrK9e832UPOfE/ZgrO+YhMyJUpJRKG8TyDmnAs\n"
            + "wLCuKfFWwkzMN9XhwVBQYIwKwVTV7QumOLsMxOFhAWKsmDIgVwnDCEtgzScPrYjgGLHZc1ZLU5l3\n"
            + "mab5/JdAJCGpNAlLkzQ7noxRSl3aZSAM9J1MwGCWhn7BgK04xmzu7FUhpdR4w5QHZCrlTXqK6gQi\n"
            + "5SVKQSkJYgyc6O7K2yr0LgHhnJ1o9Sd3vzDIbMMxaHPmrvxMSnm5mZqV1dkrkiCSMKS9lwHgnPK5\n"
            + "k83tWpbFoDHGMlK3ImIBxtgQHKN227xVr5qmmmdK9aUhTenxRSIVSJK1LnCim5YunZnTBclisUx1\n"
            + "igviuZzYmO+Nm3XMLk5gGOZa05TPSbuVLjsFe9svVAcMKzFQAODnjF3cFSBpVbLueghBEB/PoH37\n"
            + "WAVSWXVHyJRyimEYjSkvUQpS2X5xUNsk+SdSSifndPbq5bPFoYEo9REyNU4LgCBycWKP4Bi2yso7\n"
            + "tptKjYsbRodEHeQVCoC0i49QCoYlc8OFEIWHBGIqNDHGmjNVwyIicM5H/HD87VOOZShVlXf+UUm1\n"
            + "xDBlyg3SYagkIMAGJqEUvsMF5R8SyJb7F8cB9lYme6MEEYixeT8af/u/HstQDFMuBPBUqoSS8go7\n"
            + "7UVHTLEcRWnE2LCvrWUB6nmV4f5BznmAMTbvR+Nu149VIIsX3RUjwizOaZcC69RKT6a9SqXFEaXA\n"
            + "GJ3SBSB4EUA0UyfKGCAYg+D8CtJo1LEC4McTqrThZZVjhpdV3j+8rDIbAObPX7ONGLvd4eDxTplW\n"
            + "ekS3GyhSShBjJ3cBCNUqxl7LDAxrmAznApoQnIhW/Wj8Ak9/hzG8bGEJEZZzzp/hnN/EGJuUKq/M\n"
            + "XfGoYHS3U9c6dCbpGakal5V5McbyvxbI3+5d0MqApwAkugOCE4ETB2cEzgkOIaAL8a9C8CX9GcaI\n"
            + "iVXncML/cs6ncrtLlzOqPr2s8vKO+x+f7RD8KavH1Q7wKilbHVCImN4VyQKUegyM7T3izIq47RkM\n"
            + "gnNwxsAZwa3rEESTR0ysOrM/wjh90sKZRPQCMTqVJfvQpbLGbnF+/4jJVScAwIxZawwheLnX7fxM\n"
            + "EHWAsD1DWjDAiaJdAvL2PQtaAFSD0WGAYOCcW08MZ+DcAsOS+zlBcEKWyw0ivva0soXe/gLijMnV\n"
            + "J54xedGfGaPlEmAmFNI3u5ToJ/B1p5cvdAPApCnVNbrQbsvJ8kSSxSiZ9JLUg0u1XfMQAG/dNf8B\n"
            + "xrDpkPVBZssTtzr+edomrJY6RNKtueU1TocGj1P/PiN2U3+AMbJ88Y0M7CUp5XmGaUKa8kubaW9K\n"
            + "qQu4olQ8uWXS7b/zuvUNgRxfalJrMpYIziEEbe0ykOQxGZH8yoANe1gMsz2DkrGD0n7nlmzZw2c4\n"
            + "58hyu8jp0GafMam6tK+COO3W+e6RUxY/JZVcHzcSAw1pQip5yM2QJhTDkpHli05LHmdvs1GR5XG9\n"
            + "5s/2WSM/7RiiawKa4O8dFhAG9SkD7mBpPYjWWDFr8HFSlgTv8IL0n5NSZfcDWJ8xBl1o8LlchbwP\n"
            + "Bvh/m1LNzpyy+HSH07k1YRiXRGMxTUppDff5ms00TcQSCWKM/fmMydU+ALhtTqXknC4PZHsa3brD\n"
            + "vocEl1Nv1oTYdVhA3lg732SM3c+JatLXBOHEbRiU1ELr5tuT7nnagDLBLQ8RRBDMAiaIkOP1wKlp\n"
            + "146csuTnfQZG+WKfYGK6VOql9lhsYDQehwRgKtWlTSqFhGEglkjkCCF+O3JCtQMArrtpTpPH5bqx\n"
            + "KM8fAQMEZ/B5nO8Dqv5wJQuv3TF3G2PskVTAJt4hSSmP4BDM9gDeAUTYsmUFfCvYkw1T4xxFfj84\n"
            + "sbvPKq8uOtowflKx9IcOTk9FEvEVoUjEFU/YxUIpD2tTSiESiyEeT5zvcGmphWuamtue9fvc6wcW\n"
            + "BJDr80qXrr982VVTWg4bCABEjLZqYlRH6TCSbYwUnI4Y0QHPDuypGGIBTM7/djs0FOXmlpigqqMJ\n"
            + "46yKZdcAeKk5FDo7HInAMK14Ycoj26RSCEWjFDfMqrMrln0fACaUV6rauu3TCwO5WwYV5TUrZf7u\n"
            + "n4aJrg4VPati6cXE2NMpaSKkrQtiL95iT/XizBp2aXXsA8S4PXw/ucgLpRZ7YYxh976Glrb2yGWv\n"
            + "3zn31d4EMWr6sjwp1aK4ad7SEgrBtHv0MlPJU3AKB3I8nq2KqeGvrZkbAoD/fvzOAYxYxWX/UT6t\n"
            + "W0AA4Oxpy/+fQ/ArBE/e7M4wiDpmGvHUDe8MjVP67CMLTCgSRU1D40vt7Ynz37pnbq+s4DZ65qqL\n"
            + "4oaxNBhpP6UtEkn1eWfSlFLI9niQ6/H8/sWVM3/RpfbcYZVEgCopcSAlTcSsOMGpU3wRndoklArk\n"
            + "ghPIbiBykWwscvizvMj1eM5xOvmk3oDx0xkrVrXHY0/sa2k+5UA4DMPWfzPDm1QK+4NBhGOxy8+d\n"
            + "sbKyS90Vh3Mh0bjxidPJHlRKzeCMgTiBkCZDKY8AOKfUZHuyszPiB0sWgTGAc44hxQVojbSvHlWx\n"
            + "/A8vr5lV0xMgzp2+cohi6um2aPSU+paeH9+tlEKWywWPrjebSnZJjg97nvo505cPJaJXsj2eQSLt\n"
            + "xqYW/0qTJZ686baUpcsUHSRbmhBoaGnB1tq6F0KtrRe8e/8SM1M35idTlwtdo2tiprG6sS3obw6F\n"
            + "enTeC6xKLgJeLwqys99RSl394sqZn2VcsgDgpVWzdpnSvLM9FoN2UEbFeUdGld4w5JyDi7SUmROE\n"
            + "sP/Wli3GgJJAAEX+3LM0t/vaTN2bs6cvG6Br/PGW9vYHdjc2+feHQlZd6TDT2a5uSakqzs1Fvs/3\n"
            + "KJQa01UYR+QhKR2euWKL35t1iseppxaOJLtolsyeOFEnWUsWGi0PQWouOCf7M0aIJBL429YdW8Pt\n"
            + "kXM23Tl3b3dgjJq+4nxiePDzAwcGNIfDPS9RADTOcUJ+AXQhlhEw74VVMw/L0494mVhpqLGhSPRv\n"
            + "HpfT7hNIrm7AOiZJHpR9UTL74h3z9Dh1jik+TWBQYf53Pv28rgzAEa3eNmrG8iwolMUSxtJdTY2I\n"
            + "JBKgHh5EqRTg1h0YEshr1wWf+tKqmUc0X71bqwGNmr78vsKcnFtyvO5O6xQmn3ZiSM3LS3lMWpyx\n"
            + "+wQ6PMz+XSmFv/5jG+pbWs58dfWcNw4vnV05wpBmdWMwNPqLlhYYpuzZSasKkEoikJWF0tycnU5N\n"
            + "u+WFFTNeONLDdSuyGaa57kAwtMcqNHbUtzjriCPJtFikt97TUl7OuR1PrN9dugPhaBQ7GxvBif/q\n"
            + "jIlVzi5nUTNWlYdj8ae37a0fXdO0H/GEYem6afbYZpgmSv1+DPLn/t2paed3B0a3PQQAzpyyZPWA\n"
            + "PP+UkrwAWa3y9DTYljKWFjuIfSkzs0oyDKaSeH7zFmz68GMYponB+fmqNDf3kaam8A1//1XVV57o\n"
            + "yCmLXRrnj7ZFIpd/1tgIwzTRG8YYw9C8PAS83meDu2OXvvf7Bd1e87zbQP5P+aI8JsTm7w0dVOpx\n"
            + "Ojta4Yy+FD/ILjYmVwQV9iIvB4JBbNldixc/2IJgJAaHsFbjkQCGFRZKv8ezTAF3vLJ6dlOnFHza\n"
            + "smxG7JxIPLG2KRQaWLt/P3pjuL0CoGsahvj9iTyf764XV86cnjHImZj2fOaUxVfner2PnXLC4M5A\n"
            + "0mIET/MIwTmcDgc+b2rCO9t34KOaWjS0tFpdvgfdUUEcA/y5KM7OeUMT4n+g1HaAcUCdHDfNUU3B\n"
            + "4Jn1bS08GImiN2Y/SKXgd3swOD8/5nM6yz5vCD+07ZEq1aeA2Pn+8yeVDji3xJ9rZVvJtJY617Gc\n"
            + "DgcaWlvx3LvvY+ueOkRjVp8D+xppcAiBgMdjup16XCnFQrGYoyXcTskxtb1lgawsDM3Pb9A4v+DV\n"
            + "NXM2Z/r4GXs7QiyWKN+3/8Dmwpxshy54quMqCcNUCg2tbXjh/c14b8dOeylv6iQDhypBRBNx7DkQ\n"
            + "4xLKZWUjVjbXa6YUCrKz8e3Cog8U1P99dc2cnT3xNRkDQlDbG1va1u8PBssHFeRDEwKcMzS2tGLn\n"
            + "vgb8fecufFJrrYJHtjSpI1gAjdJ8SfXCAmpKKWicY1BBAUr9/t8nzET5a3fMq+uxRCGT7n5aWeUw\n"
            + "r8v18ujhPyje09iIt7duR21DI5qCISipeveJzpA5hMCwkhJku93zDCnXvLl2Xo9OYs34Qso/qViy\n"
            + "sDkYmv9RTQ0Y9d/JUwqAUxM4ZfCQiK6JCdJMbHzjrkrZ09+b8Tfs1H1UV1lycvEvfW734Lb2MNAf\n"
            + "530qINfrxcmlpXWc0eRNd9z2h9766ow/wp/9ZZ00lRo7KD/f0nupkLb2RN/epAKkRGkggGGlpW8y\n"
            + "RhdtWtt7MHrEQ6xcHa96nc6H87K81ze0tPQjz1A4aeBA5OXkPMlAZa+vva2ht0+hx96O8JOpi09p\n"
            + "DYdf+HjX7oLeKmV068nkHP8ydKjMcrkeeX3tvKP2ts8ei7qb7rjtwxyvd31pXh5gGtbcrr64SRNe\n"
            + "p45/GTK4zet0Tj+aMHpMspKWMMxlxfn5F0TjsRH7GuoB4n3LLUwTeYE8DC4p3ud06GNfXzvvT0f7\n"
            + "lHr8DTsjJiwIKKU+215Tk90abOs7WZdSKC0uRmlBYS2AUW/ds2BnXzitXnnl0emTFg6PRqMvfLp7\n"
            + "l689Ejm6UJQC5xwDi4pRXFDwciQSuXDzA0ujfcVpe+2lYGeWV1/SGgxt3PV5bXY4FAKORqNRSjh0\n"
            + "HUMGDkQgN/cOJY05b92zMNaXVLRX39J2xsSF58QTsT/vqq0VzQeaAU69CsPr9eJbQ4fCqTtvMU3z\n"
            + "wXfXL+xz6V+vv+nztAnzvw2wVz7fs6ekvrERCr2xfDmDPzcHQwcPbmVEF76zbuGbfTX9PiqvXh0+\n"
            + "YUG+4PzuxqbGy+v31Wuh5BCdTIYW+7LcbjcKCvJVYUHhc0qp8rfXVe7oy+2ho/Yu3O+Nm+3wOt0X\n"
            + "RyKRua2trT/cV1+PSDBkyVh3gr71skI43W4UFhYhJzt7q9vjXhJNxP7w/vrFYfRxO+ovJx5+67ws\n"
            + "CVwJpRYHg8HCfXv3ItjWZp8dOzwQALxeL4pKSpDly24hhmoG9tC791X3m/pNn3lbdO4NY9mJ+sDr\n"
            + "GNG4WDR6UvOBA95gsE2PRCKQpplauCV5toxZsYGI4HK54PVlx3Nz/SGX27lTSvlwIhHd8MH9KxLo\n"
            + "Z9ZngHRqTE6sPImIRiqlTjZM84RYNFpsxON+KaVPKkXEmCKiNqFpLbrTWceFqCHGPpKm/Ovb6yo/\n"
            + "Rj+2Pgkk3X48fr6TE3lB5AKQvhRFTEoVU9IM/m199TGzFG2fB/JNMzp+C44DOW7HgfQf+/9f2EbQ\n"
            + "ZgS2DAAAAABJRU5ErkJggg==";
}
