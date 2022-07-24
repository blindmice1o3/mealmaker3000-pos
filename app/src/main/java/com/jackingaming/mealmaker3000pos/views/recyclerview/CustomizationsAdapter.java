package com.jackingaming.mealmaker3000pos.views.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackingaming.mealmaker3000pos.R;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.AddInCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.CupOptionCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.Customization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.EspressoShotCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.FlavorCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.MilkCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.SweetenerCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.TeaCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.ToppingCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.UnknownCustomization;

import java.util.List;

public class CustomizationsAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ADD_IN = 0;
    private static final int VIEW_TYPE_CUP_OPTION = 1;
    private static final int VIEW_TYPE_ESPRESSO_SHOT = 2;
    private static final int VIEW_TYPE_FLAVOR = 3;
    private static final int VIEW_TYPE_MILK = 4;
    private static final int VIEW_TYPE_SWEETENER = 5;
    private static final int VIEW_TYPE_TEA = 6;
    private static final int VIEW_TYPE_TOPPING = 7;
    private static final int VIEW_TYPE_UNKNOWN = 8;

    public interface OnItemClickListener {
        void onCustomizationClick(Drink drink, int positionAbsoluteAdapter);
    }

    private OnItemClickListener listener;

    private Drink drink;
    private List<Customization> customizations;

    public CustomizationsAdapter(Drink drink, OnItemClickListener listener) {
        this.drink = drink;
        this.customizations = drink.getCustomizations();
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Customization customization = customizations.get(position);

        if (customization instanceof AddInCustomization) {
            return VIEW_TYPE_ADD_IN;
        } else if (customization instanceof CupOptionCustomization) {
            return VIEW_TYPE_CUP_OPTION;
        } else if (customization instanceof EspressoShotCustomization) {
            return VIEW_TYPE_ESPRESSO_SHOT;
        } else if (customization instanceof FlavorCustomization) {
            return VIEW_TYPE_FLAVOR;
        } else if (customization instanceof MilkCustomization) {
            return VIEW_TYPE_MILK;
        } else if (customization instanceof SweetenerCustomization) {
            return VIEW_TYPE_SWEETENER;
        } else if (customization instanceof TeaCustomization) {
            return VIEW_TYPE_TEA;
        } else if (customization instanceof ToppingCustomization) {
            return VIEW_TYPE_TOPPING;
        } else {
            return VIEW_TYPE_UNKNOWN;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = null;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == VIEW_TYPE_ADD_IN) {
            itemView = inflater.inflate(R.layout.rv_customization_add_in, parent, false);
            viewHolder = new AddInViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_CUP_OPTION) {
            itemView = inflater.inflate(R.layout.rv_customization_cup_option, parent, false);
            viewHolder = new CupOptionViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_ESPRESSO_SHOT) {
            itemView = inflater.inflate(R.layout.rv_customization_espresso_shot, parent, false);
            viewHolder = new EspressoShotViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_FLAVOR) {
            itemView = inflater.inflate(R.layout.rv_customization_flavor, parent, false);
            viewHolder = new FlavorViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_MILK) {
            itemView = inflater.inflate(R.layout.rv_customization_milk, parent, false);
            viewHolder = new MilkViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_SWEETENER) {
            itemView = inflater.inflate(R.layout.rv_customization_sweetener, parent, false);
            viewHolder = new SweetenerViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_TEA) {
            itemView = inflater.inflate(R.layout.rv_customization_tea, parent, false);
            viewHolder = new TeaViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_TOPPING) {
            itemView = inflater.inflate(R.layout.rv_customization_topping, parent, false);
            viewHolder = new ToppingViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_UNKNOWN) {
            itemView = inflater.inflate(R.layout.rv_customization_unknown, parent, false);
            viewHolder = new UnknownViewHolder(itemView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_ADD_IN) {
            AddInViewHolder addInViewHolder = (AddInViewHolder) holder;
            addInViewHolder.bindData(addInViewHolder, position);
        } else if (holder.getItemViewType() == VIEW_TYPE_CUP_OPTION) {
            CupOptionViewHolder cupOptionViewHolder = (CupOptionViewHolder) holder;
            cupOptionViewHolder.bindData(cupOptionViewHolder, position);
        } else if (holder.getItemViewType() == VIEW_TYPE_ESPRESSO_SHOT) {
            EspressoShotViewHolder espressoShotViewHolder = (EspressoShotViewHolder) holder;
            espressoShotViewHolder.bindData(espressoShotViewHolder, position);
        } else if (holder.getItemViewType() == VIEW_TYPE_FLAVOR) {
            FlavorViewHolder flavorViewHolder = (FlavorViewHolder) holder;
            flavorViewHolder.bindData(flavorViewHolder, position);
        } else if (holder.getItemViewType() == VIEW_TYPE_MILK) {
            MilkViewHolder milkViewHolder = (MilkViewHolder) holder;
            milkViewHolder.bindData(milkViewHolder, position);
        } else if (holder.getItemViewType() == VIEW_TYPE_SWEETENER) {
            SweetenerViewHolder sweetenerViewHolder = (SweetenerViewHolder) holder;
            sweetenerViewHolder.bindData(sweetenerViewHolder, position);
        } else if (holder.getItemViewType() == VIEW_TYPE_TEA) {
            TeaViewHolder teaViewHolder = (TeaViewHolder) holder;
            teaViewHolder.bindData(teaViewHolder, position);
        } else if (holder.getItemViewType() == VIEW_TYPE_TOPPING) {
            ToppingViewHolder toppingViewHolder = (ToppingViewHolder) holder;
            toppingViewHolder.bindData(toppingViewHolder, position);
        } else {
            UnknownViewHolder unknownViewHolder = (UnknownViewHolder) holder;
            unknownViewHolder.bindData(unknownViewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return customizations.size();
    }

    // 0 AddInViewHolder ==================================================

    public class AddInViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLineTheCup;
        private TextView tvPowder;

        public AddInViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLineTheCup = (TextView) itemView.findViewById(R.id.tv_line_the_cup);
            tvPowder = (TextView) itemView.findViewById(R.id.tv_powder);
        }

        public void bindData(RecyclerView.ViewHolder addInViewHolder, int position) {
            AddInCustomization addInCustomization = (AddInCustomization) customizations.get(position);

            if (addInCustomization.getLineTheCup() != null) {
                tvLineTheCup.setText("line the cup: " + addInCustomization.getLineTheCup().toString());
            } else {
                Log.d("CustomizationsAdapter", "AddInViewHolder.bindData() addInCustomization.getLineTheCup() == null");
                tvLineTheCup.setText("line the cup: null");
            }

            if (addInCustomization.getPowder() != null) {
                tvPowder.setText("powder: " + addInCustomization.getPowder().toString());
            } else {
                Log.d("CustomizationsAdapter", "AddInViewHolder.bindData() addInCustomization.getPowder() == null");
                tvPowder.setText("powder: null");
            }

            addInViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("CustomizationsAdapter", "AddInViewHolder onClick(View)");
                    if (listener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition();
                        Log.i("CustomizationsAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) {
                            listener.onCustomizationClick(drink, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    // 1 CupOptionViewHolder ==================================================

    public class CupOptionViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCupSize;

        public CupOptionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCupSize = (TextView) itemView.findViewById(R.id.tv_cup_size);
        }

        public void bindData(RecyclerView.ViewHolder cupOptionViewHolder, int position) {
            CupOptionCustomization cupOptionCustomization = (CupOptionCustomization) customizations.get(position);
            tvCupSize.setText("cup size: " + cupOptionCustomization.getCupSize().toString());

            cupOptionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("CustomizationsAdapter", "CupOptionViewHolder onClick(View)");
                    if (listener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition();
                        Log.i("CustomizationsAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) {
                            listener.onCustomizationClick(drink, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    // 2 EspressoShotViewHolder ==================================================

    public class EspressoShotViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRoast;
        private TextView tvQuantity;
        private TextView tvType;
        private TextView tvPrep;

        public EspressoShotViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoast = (TextView) itemView.findViewById(R.id.tv_roast);
            tvQuantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            tvPrep = (TextView) itemView.findViewById(R.id.tv_prep);
        }

        public void bindData(RecyclerView.ViewHolder espressoShotViewHolder, int position) {
            EspressoShotCustomization espressoShotCustomization = (EspressoShotCustomization) customizations.get(position);
            tvRoast.setText("roast: " + espressoShotCustomization.getRoast().toString());
            tvQuantity.setText("quantity: " + espressoShotCustomization.getQuantity().toString());
            tvType.setText("type: " + espressoShotCustomization.getType().toString());
            tvPrep.setText("prep: " + espressoShotCustomization.getPrep().toString());

            espressoShotViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("CustomizationsAdapter", "EspressoShotViewHolder onClick(View)");
                    if (listener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition();
                        Log.i("CustomizationsAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) {
                            listener.onCustomizationClick(drink, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    // 3 FlavorViewHolder ==================================================

    public class FlavorViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSauce;
        private TextView tvSyrup;

        public FlavorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSauce = (TextView) itemView.findViewById(R.id.tv_sauce);
            tvSyrup = (TextView) itemView.findViewById(R.id.tv_syrup);
        }

        public void bindData(RecyclerView.ViewHolder flavorViewHolder, int position) {
            FlavorCustomization flavorCustomization = (FlavorCustomization) customizations.get(position);
            tvSauce.setText("sauce: " + flavorCustomization.getSauce().toString());
            tvSyrup.setText("syrup: " + flavorCustomization.getSyrup().toString());

            flavorViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("CustomizationsAdapter", "FlavorViewHolder onClick(View)");
                    if (listener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition();
                        Log.i("CustomizationsAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) {
                            listener.onCustomizationClick(drink, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    // 4 MilkViewHolder ==================================================

    public class MilkViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFoam;
        private TextView tvType;
        private TextView tvTemperature;

        public MilkViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoam = (TextView) itemView.findViewById(R.id.tv_foam);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            tvTemperature = (TextView) itemView.findViewById(R.id.tv_temperature);
        }

        public void bindData(RecyclerView.ViewHolder milkViewHolder, int position) {
            MilkCustomization milkCustomization = (MilkCustomization) customizations.get(position);
            tvFoam.setText("foam: " + milkCustomization.getFoam().toString());
            tvType.setText("type: " + milkCustomization.getType().toString());
            tvTemperature.setText("temperature: " + milkCustomization.getTemperature().toString());

            milkViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("CustomizationsAdapter", "MilkViewHolder onClick(View)");
                    if (listener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition();
                        Log.i("CustomizationsAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) {
                            listener.onCustomizationClick(drink, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    // 5 SweetenerViewHolder ==================================================

    public class SweetenerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLiquid;
        private TextView tvPacket;

        public SweetenerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLiquid = (TextView) itemView.findViewById(R.id.tv_liquid);
            tvPacket = (TextView) itemView.findViewById(R.id.tv_packet);
        }

        public void bindData(RecyclerView.ViewHolder sweetenerViewHolder, int position) {
            SweetenerCustomization sweetenerCustomization = (SweetenerCustomization) customizations.get(position);
            tvLiquid.setText("liquid: " + sweetenerCustomization.getLiquid().toString());
            tvPacket.setText("packet: " + sweetenerCustomization.getPacket().toString());

            sweetenerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("CustomizationsAdapter", "SweetenerViewHolder onClick(View)");
                    if (listener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition();
                        Log.i("CustomizationsAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) {
                            listener.onCustomizationClick(drink, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    // 6 TeaViewHolder ==================================================

    public class TeaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAddChai;

        public TeaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddChai = (TextView) itemView.findViewById(R.id.tv_add_chai);
        }

        public void bindData(RecyclerView.ViewHolder teaViewHolder, int position) {
            TeaCustomization teaCustomization = (TeaCustomization) customizations.get(position);
            tvAddChai.setText("add chai: " + teaCustomization.getAddChai().toString());

            teaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("CustomizationsAdapter", "TeaViewHolder onClick(View)");
                    if (listener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition();
                        Log.i("CustomizationsAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) {
                            listener.onCustomizationClick(drink, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    // 7 ToppingViewHolder ==================================================

    public class ToppingViewHolder extends RecyclerView.ViewHolder {
        private TextView tvColdFoam;
        private TextView tvCinnamonPowder;
        private TextView tvDrizzle;
        private TextView tvCinnamonDolceSprinkles;
        private TextView tvWhippedCream;

        public ToppingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvColdFoam = (TextView) itemView.findViewById(R.id.tv_cold_foam);
            tvCinnamonPowder = (TextView) itemView.findViewById(R.id.tv_cinnamon_powder);
            tvDrizzle = (TextView) itemView.findViewById(R.id.tv_drizzle);
            tvCinnamonDolceSprinkles = (TextView) itemView.findViewById(R.id.tv_cinnamon_dolce_sprinkles);
            tvWhippedCream = (TextView) itemView.findViewById(R.id.tv_whipped_cream);
        }

        public void bindData(RecyclerView.ViewHolder toppingViewHolder, int position) {
            ToppingCustomization toppingCustomization = (ToppingCustomization) customizations.get(position);
            tvColdFoam.setText("cold foam: " + toppingCustomization.getColdFoam().toString());
            tvCinnamonPowder.setText("cinnamon powder: " + toppingCustomization.getCinnamonPowder().toString());
            tvDrizzle.setText("drizzle: " + toppingCustomization.getDrizzle().toString());
            tvCinnamonDolceSprinkles.setText("cinnamon dolce sprinkles: " + toppingCustomization.getCinnamonDolceSprinkles().toString());
            tvWhippedCream.setText("whipped cream: " + toppingCustomization.getWhippedCream().toString());

            toppingViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("CustomizationsAdapter", "ToppingViewHolder onClick(View)");
                    if (listener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition();
                        Log.i("CustomizationsAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) {
                            listener.onCustomizationClick(drink, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

    // 8 UnknownViewHolder ==================================================

    public class UnknownViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public UnknownViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }

        public void bindData(RecyclerView.ViewHolder unknownViewHolder, int position) {
            UnknownCustomization unknownCustomization = (UnknownCustomization) customizations.get(position);
            tvName.setText(unknownCustomization.getName());

            unknownViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("CustomizationsAdapter", "UnknownViewHolder onClick(View)");
                    if (listener != null) {
                        int positionAbsoluteAdapter = getAbsoluteAdapterPosition();
                        Log.i("CustomizationsAdapter", "positionAbsoluteAdapter: " + positionAbsoluteAdapter);
                        if (positionAbsoluteAdapter != RecyclerView.NO_POSITION) {
                            listener.onCustomizationClick(drink, positionAbsoluteAdapter);
                        }
                    }
                }
            });
        }
    }

}